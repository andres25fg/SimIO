import javax.swing.*;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/**
 * Clase Simualtion
 *
 * Esta clase contiene la definición de la clase principal de la simulación que se encarga de comunicarse con las demás clases del sistema
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class Simulation  {
    private double clock; // Reloj del sistema
    private boolean slowMode; // Booleano para saber si la simulación se va a hacer en modo lento
    private int slowModeSeconds; // Segundos de la simulación para el modo lento
    private int numSimulations; // Número de veces que se va a realizar la simulación
    private int secondsSimulation; // Segundos para la simulación normal
    private int timeOut=100; // Segundos que tiene una conexion para ser atendida
    private double lambda = 1/1.7143; // llega un cliente cada 1.7143 segunos (60/35)
    private ClientAdministratorModule clientAdministrator; // Client Administrator
    private ProcessAdministratorModule processAdministrator; // Process Administrator
    private QueryExecutionsModule queryExecutions; // Query Exections
    private QueryProcessorModule queryProcessor; // Query Processor
    private TransactionsModule transactions; // Transactions
    private PriorityQueue<QueryEvent> eventList; // Lista de eventos del sistema
    public StatisticsModule statistics = new StatisticsModule();
    private int numConections=0;
    private int numConectionServed=0;
    private int numTimeOut=0;
    private int numRejected=0;
    private UserInterface userInterface;

    /**
     * Método constructor
     *  @param numSims Número de veces que se va a realizar la simulación
     * @param secsSim Segundos para la simulación normal
     * @param slowMode Booleano para saber si la simulación se va a hacer en modo lento
     * @param slowModeSecs Segundos de la simulación para el modo lento
     * @param timeOut Segundos que tiene una conexion para ser atendida
     * @param k Cantidad de conexiones que el sistema maneja concurrentemente
     * @param n Cantidad de procesos que el procesador de consultas puede manejar
     * @param p Cantidad de procesos para la ejecución de transacciones
     * @param m Cantidad de procesos para ejecutar consultas
     * @param gui
     */
    public Simulation(int numSims, int secsSim, boolean slowMode, int slowModeSecs, int timeOut, int k, int n, int p, int m, UserInterface gui){
        userInterface = gui;
        this.setClock(0); // Inicializamos el reloj en el tiempo 0
        this.setSecondsSimulation(secsSim); // Guardamos la cantidad de segundos por simulación
        this.setNumSimulations(numSims); // Se guarda el número de simulaciones que se van a realizar
        this.setSlowMode(slowMode); // Se guarda la bandera del Slow Mode
        this.setSlowModeSeconds(slowModeSecs); // Se guardan los segundos del delay para el Slow Mode
        this.setTimeOut(timeOut); // Se guarda el tiempo del timeOut de las conexiones

        Comparator<QueryEvent> comparator = new QueryComparator(); // Creamos el comparador que utiliza la cola de prioridades
        PriorityQueue<QueryEvent> stack = new PriorityQueue<QueryEvent>(comparator); // Instanciamos la cola de prioridades con el
        setStackQueries(stack);

        (new Thread()
        {
            public void run(){
                try {
                    beginSimulation(k, n, p, m);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }).start();

    }

    public void setStackQueries(PriorityQueue<QueryEvent> stackQueries) {
        this.eventList = stackQueries;
    }

    public PriorityQueue<QueryEvent> getStackQueries() {
        return eventList;
    }

    /**
     * Método que agrega un QueryEvent a la cola de eventos
     * @param newEvent
     */
    private void addQueryEvent(QueryEvent newEvent) {
        eventList.add(newEvent);
    }

    /**
     * Método que retorna el número de conexiones que hay dentro de la cola de prioridades
     * @return
     */
    public int getPriorityQueueSize() {
        return eventList.size();
    }

    /**
     * Método que saca el siguiente evento de la cola y lo elimina de la cola
     * @return QueryEvent evento que se encuentra en la cabeza de la cola de eventos.
     */
    private QueryEvent getNextEvent() {
        return eventList.poll();
    }

    public void beginSimulation(int k,int n, int p, int m) throws InterruptedException {
        for(int i=0; i<numSimulations; i++) {
            //para las llegadas agrego una conexion de tipo nulo

            // Creamos los objetos específicos de cada módulo con el cual se comunica Simulation
            this.clientAdministrator = new ClientAdministratorModule(k);
            this.processAdministrator = new ProcessAdministratorModule(1);
            this.queryExecutions = new QueryExecutionsModule(m);
            this.queryProcessor = new QueryProcessorModule(n);
            this.transactions = new TransactionsModule(p);
            this.statistics = new StatisticsModule();

            RandomGenerator random = new RandomGenerator();
            clock = 0;
            eventList.clear();
            QueryEvent firstArrival = new QueryEvent(0,EventType.values()[0],null);
            eventList.add(firstArrival);

            //prueba
            userInterface.showTextinGUI("Sumulación número: " + i);
            //System.out.println("simulacion "+i);
            //prueba

            while(secondsSimulation > clock) {
                procesEvent();
                if(slowMode) {
                    try {
                        TimeUnit.SECONDS.sleep(slowModeSeconds);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }
                //prueba
                userInterface.showTextinGUI("Reloj: " + round(clock));
                // System.out.println("reloj "+clock);
                //prueba
                QueryEvent nextArrival = new QueryEvent(random.poisson(lambda),EventType.values()[0],null);
                eventList.add(nextArrival);
                // Server data is refreshed on the GUI
                userInterface.showServersInformation(clientAdministrator.getFreeServers(),processAdministrator.getFreeServers(),queryProcessor.getFreeServers(),queryExecutions.getFreeServers(),transactions.getFreeServers());
            }
            userInterface.showTextinGUI("\n---- Estádisticas globales del sistema:\n");
            userInterface.showTextinGUI("Número de conexiones: "+ numConections);
            userInterface.showTextinGUI("Total de conexiones atendidas:" + numConectionServed);
            userInterface.showTextinGUI("Total de conexiones que hicieron timeout: "+ numTimeOut);
            userInterface.showTextinGUI("Total de conexiones rechazadas: "+ numRejected);

            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +statistics.getSelectAverageTime()+" num "+statistics.getNumSelect());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "    +statistics.getDdlAverageTime()+" num "+statistics.getNumDdl());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "   +statistics.getJoinAverageTime()+" num "+statistics.getNumJoin());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: " +statistics.getUpdateAverageTime()+" num "+statistics.getNumUdpate());

            userInterface.showTextinGUI("\n---- Estádisticas de cada módulo:\n"); // double roundOff = Math.round(a * 100.0) / 100.0;
            userInterface.showTextinGUI("Módulo: Administración de Clientes");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +clientAdministrator.getStatistic().getSelectAverageTime()+" para "+clientAdministrator.getStatistic().getNumSelect() + " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +clientAdministrator.getStatistic().getDdlAverageTime()+" para "+clientAdministrator.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+clientAdministrator.getStatistic().getJoinAverageTime()+" para "+clientAdministrator.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+clientAdministrator.getStatistic().getUpdateAverageTime()+" para "+clientAdministrator.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+clientAdministrator.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +clientAdministrator.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +clientAdministrator.getStatistic().getP(clientAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("ws: "+clientAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("wq metodo getStackAverageTime: "+clientAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("u: " +clientAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("w: " +clientAdministrator.getStatistic().getW());
            userInterface.showTextinGUI("L: "+clientAdministrator.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+clientAdministrator.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+clientAdministrator.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Administrador de procesos");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+processAdministrator.getStatistic().getSelectAverageTime()+" para "+processAdministrator.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+processAdministrator.getStatistic().getDdlAverageTime()+" para "+processAdministrator.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+processAdministrator.getStatistic().getJoinAverageTime()+" para "+processAdministrator.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+processAdministrator.getStatistic().getUpdateAverageTime()+" para "+processAdministrator.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+processAdministrator.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +processAdministrator.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +processAdministrator.getStatistic().getP(processAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("ws: "+processAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("wq metodo getStackAverageTime: "+processAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("u: " +processAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("w: " +processAdministrator.getStatistic().getW());
            userInterface.showTextinGUI("L: "+processAdministrator.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+processAdministrator.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+processAdministrator.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Ejecutor de sentencias");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+queryExecutions.getStatistic().getSelectAverageTime()+" para "+queryExecutions.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+queryExecutions.getStatistic().getDdlAverageTime()+" para "+queryExecutions.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryExecutions.getStatistic().getJoinAverageTime()+" para "+queryExecutions.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryExecutions.getStatistic().getUpdateAverageTime()+" para "+queryExecutions.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+queryExecutions.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +queryExecutions.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +queryExecutions.getStatistic().getP(queryExecutions.getMaxSimConnections()));
            userInterface.showTextinGUI("ws: "+queryExecutions.getStatistic().getWs());
            userInterface.showTextinGUI("wq metodo getStackAverageTime: "+queryExecutions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("u: " +queryExecutions.getStatistic().getU());
            userInterface.showTextinGUI("w: " +queryExecutions.getStatistic().getW());
            userInterface.showTextinGUI("L: "+queryExecutions.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+queryExecutions.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+queryExecutions.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Procesador de consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+queryProcessor.getStatistic().getSelectAverageTime() +" para "+queryProcessor.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+queryProcessor.getStatistic().getDdlAverageTime()+" para "+queryProcessor.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryProcessor.getStatistic().getJoinAverageTime()+" para "+queryProcessor.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryProcessor.getStatistic().getUpdateAverageTime()+" para "+queryProcessor.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+queryProcessor.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +queryProcessor.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +queryProcessor.getStatistic().getP(queryProcessor.getMaxSimConnections()));
            userInterface.showTextinGUI("ws: "+queryProcessor.getStatistic().getWs());
            userInterface.showTextinGUI("wq metodo getStackAverageTime: "+queryProcessor.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("u: " +queryProcessor.getStatistic().getU());
            userInterface.showTextinGUI("w: " +queryProcessor.getStatistic().getW());
            userInterface.showTextinGUI("L: "+queryProcessor.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+queryProcessor.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+queryProcessor.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Transacciones");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+transactions.getStatistic().getSelectAverageTime()+" para "+transactions.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+transactions.getStatistic().getDdlAverageTime()+" para "+transactions.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+transactions.getStatistic().getJoinAverageTime()+" para "+transactions.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+transactions.getStatistic().getUpdateAverageTime()+" para "+transactions.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+transactions.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +transactions.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +transactions.getStatistic().getP(transactions.getMaxSimConnections()));
            userInterface.showTextinGUI("ws: "+transactions.getStatistic().getWs());
            userInterface.showTextinGUI("wq metodo getStackAverageTime: "+transactions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("u: " +transactions.getStatistic().getU());
            userInterface.showTextinGUI("w: " +transactions.getStatistic().getW());
            userInterface.showTextinGUI("L: "+transactions.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+transactions.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+transactions.getStatistic().getLq());

            userInterface.activateReturnButton();
            //se crea html con estadisticas
        }
    }

    /**
     * procesa el primer elemento de la lista de eventos
     */

    public boolean checkTimeOut(Connection c){
        boolean time_out = false;
        if(c.getArrivalTime()+timeOut<= clock){
            QueryEvent event = new QueryEvent(clock, EventType.values()[2], c);
            eventList.add(event);
            time_out = true;
        }
        return time_out;
    }

    public void procesEvent(){
        QueryEvent actualEvent = this.getNextEvent();

        clock =  actualEvent.getEventTime();
        //se procesa segun el tipo de evento

        //prueba
        userInterface.showTextinGUI("\nEvento actual: " + actualEvent.getType());
        //prueba

        switch (actualEvent.getType()) {
            case "CONNECTION_IN":
                numConections++;
                if (!clientAdministrator.checkMaxConnections()) { // Se revisa si el Client Administrator tiene servidores libres o no
                    clientAdministrator.rejectConnection(); // Si no tiene servidores libres se rechaza la conexión
                    numRejected++;

                } else { // Si hay servidores libres, se crea la conexión

                    Connection newConnection = clientAdministrator.createConnection();
                    newConnection.setCurrentModule(ModuleFlag.values()[0]);
                    clientAdministrator.arrive(newConnection, clock);
                    newConnection.setType();
                    double serviceTime = processAdministrator.generateServiceTime();
                    clientAdministrator.updateStatistics(newConnection, serviceTime, clock);
                    QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], newConnection);
                    eventList.add(event);
                }
                break;
            case "CONNECTION_OUT":
                numConectionServed++;
                Connection out = actualEvent.getConnection();
                updateStatistics(out.getType().toString(), out.getArrivalTime());
                clientAdministrator.exit(clock);
                break;
            case "TIME_OUT":
                clientAdministrator.exit(clock);
                numTimeOut++;
                break;
            case "EXIT_MODULE":
                //la conexion sale del modulo en el que se encuentra y pasa al siguente
                Connection actualConnection = actualEvent.getConnection();
                ModuleFlag actualModule = actualConnection.getCurrentModule(); // se busca el modulo actual
                userInterface.showTextinGUI("modulo: " + actualModule.getModule());
                // si ya paso el tiempo de servicio se crea un evento de tipo time out
                boolean processing;
                //se busca el modulo en el que esta la conexion
                switch (actualModule.getModule()) {
                    case "CLIENT_ADMIN":
                        // se revisa si la conexion ya paso por el modulo de transacciones

                        if (checkTimeOut(actualConnection) == false) {
                            if (!actualConnection.getTransactionModule()) {
                                //aun no pasa por el modulo de transacciones
                                processing = processAdministrator.arrive(actualConnection, clock); // el proceso llega el siguente modulo
                                if (processing == true) {
                                    // si es atendido
                                    //se calcula el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                                    // y se añade el evento a la lista de eventos
                                    double serviceTime = processAdministrator.generateServiceTime();
                                    actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                                    processAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                                    QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                    eventList.add(event);
                                }
                                //ya paso por el modulo de transacciones
                            } else {
                                // se saca el proceso del modulo
                                //se crea un evento de tipo connection_out
                                QueryEvent event = new QueryEvent(clock, EventType.values()[1], actualConnection);
                                eventList.add(event);
                            }
                        }
                        break;

                    case "PROCESS_ADMIN":
                        //como el proceso esta saliendo se ejecuta el metodo exit el cual devuelve una conexion si la cola del modulo no esta vacia
                        Connection client_p = processAdministrator.exit(clock);
                        if (client_p != null) {
                            //la cola no esta vacia
                            //se calcula el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                            // y se añade el evento a la lista de eventos
                            double serviceTime = processAdministrator.generateServiceTime();
                            actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                            processAdministrator.updateStatistics(client_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_p);
                            eventList.add(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            processing = queryProcessor.arrive(actualConnection, clock); // el proceso llega el siguente modulo
                            if (processing == true) {
                                // si es atendido
                                //se calcula el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                                // y se añade el evento a la lista de eventos
                                double serviceTime = queryProcessor.generateServiceTime(actualConnection.getType().getReadOnly());
                                actualConnection.setCurrentModule(ModuleFlag.values()[2]);
                                queryProcessor.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                        }
                        break;

                    case "QUERY_PROCESSOR":
                        //como el proceso esta saliendo se ejecuta el metodo exit el cual devuelve una conexion si la cola del modulo no esta vacia
                        Connection client_q_p = queryProcessor.exit(clock);
                        if (client_q_p != null) {
                            //la cola no esta vacia
                            //se calcula el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                            // y se añade el evento a la lista de eventos
                            double serviceTime = queryProcessor.generateServiceTime(client_q_p.getType().getReadOnly());
                            actualConnection.setCurrentModule(ModuleFlag.values()[2]);
                            queryProcessor.updateStatistics(client_q_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_q_p);
                            eventList.add(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            processing = transactions.arrive(actualConnection, clock);// el proceso llega el siguente modulo
                            if (processing == true) {
                                // si es atendido
                                //se calcula el numero de bloques de disco leidos, el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                                // y se añade el evento a la lista de eventos
                                double diskBloks = transactions.loadDiskBloks(actualConnection.getType().toString());
                                actualConnection.setBlocksRead(diskBloks);
                                actualConnection.setCurrentModule(ModuleFlag.values()[3]);
                                double serviceTime = transactions.generateServiceTime(diskBloks);
                                transactions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                        }
                        break;

                    case "TRANSACTION":

                        Connection client_t = transactions.exit(clock);
                        if (client_t != null) {
                            double diskBloks = transactions.loadDiskBloks(actualConnection.getType().toString());
                            actualConnection.setBlocksRead(diskBloks);
                            client_t.setCurrentModule(ModuleFlag.values()[3]);
                            double serviceTime = transactions.generateServiceTime(diskBloks);
                            client_t.setCurrentModule(ModuleFlag.values()[3]);
                            transactions.updateStatistics(client_t, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_t);
                            eventList.add(event);
                        }
                        actualConnection.setTransactionModuleTrue();
                        if (checkTimeOut(actualConnection) == false) {
                            processing = queryExecutions.arrive(actualConnection, clock);
                            if (processing == true) {
                                double serviceTime = queryExecutions.generateServiceTime(actualConnection.getBlocksRead(), actualConnection.getType().toString());
                                actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                queryExecutions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                        }
                        break;

                    case "QUERY_EXE":

                        Connection client_q = queryExecutions.exit(clock);
                        if (client_q != null) {
                            double serviceTime = queryExecutions.generateServiceTime(actualConnection.getBlocksRead(), actualConnection.getType().toString());
                            actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                            actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                            queryExecutions.updateStatistics(client_q, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                            eventList.add(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            //regresa al modulo de administracion de clientes
                            double serviceTime = actualConnection.getBlocksRead() / 2;
                            actualConnection.setCurrentModule(ModuleFlag.values()[0]);
                            clientAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[1], actualConnection);
                            eventList.add(event);
                        }
                        break;
                }
                break;
        }
    }

    public void updateStatistics(String type, double arrival){
        switch (type){
            case "UPDATE":
                statistics.setUpdateAverageTime(clock-arrival);
                break;
            case "SELECT":
                statistics.setSelectAverageTime(clock-arrival);
                break;
            case "DDL":
                statistics.setDdlAverageTime(clock-arrival);
                break;
            case "JOIN":
                statistics.setJoinAverageTime(clock-arrival);
        }
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void setSlowMode(boolean slowMode) {
        this.slowMode = slowMode;
    }

    public void setSlowModeSeconds(int slowModeSeconds) {
        this.slowModeSeconds = slowModeSeconds;
    }

    public void setNumSimulations(int numSimulations) {
        this.numSimulations = numSimulations;
    }

    public void setSecondsSimulation(int secondsSimulation) {
        this.secondsSimulation = secondsSimulation;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public double round(double number){
        number = Math.round(number*1000);
        return number/1000;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        //prueba
        int secSim=1000;
        int numSim =1;
        boolean slowMode=false;
        int slowModeSecs=0;
        //UserInterface gui = null; // Esto es para poder usar pruebas por consola
        //Simulation simulation = new Simulation(numSim, secSim,slowMode,slowModeSecs,100,5,5,5,5, gui);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }



    public class QueryComparator implements Comparator<QueryEvent> {
        /**
         * Método que realiza la comparación de prioridades de la cola
         * @param query1
         * @param query2
         * @return
         */
        public int compare(QueryEvent query1, QueryEvent query2) {
            //Hace una resta entre las prioridades de cada conexión para determinar el orden
            int priority;
            if(query1.getEventTime() - query2.getEventTime()>0){
                priority=1;
            }else{
                priority = -1;
            }
            return priority;
        }
    }
}

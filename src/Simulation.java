import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
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
    private StatisticsModule statistics = new StatisticsModule();
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void setStackQueries(PriorityQueue<QueryEvent> stackQueries) {
        this.eventList = stackQueries;
    }

    public PriorityQueue<QueryEvent> getStackQueries() {
        return this.eventList;
    }

    /**
     * Método que agrega un QueryEvent a la cola de eventos
     * @param newEvent
     */
    private void addQueryEvent(QueryEvent newEvent) {
        this.eventList.add(newEvent);
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

    public void beginSimulation(int k,int n, int p, int m) throws Exception {
        generateHTMLindex(getNumSimulations());
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
            addQueryEvent(firstArrival);

            //prueba
            userInterface.showTextinGUI("Sumulación número: " + (i+1));
            userInterface.showSimulationNumber(i+1);
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
                if(slowMode) {
                    userInterface.showTextinGUI("Reloj: " + round(clock));
                }
                userInterface.showClock(round(clock));
                // System.out.println("reloj "+clock);
                //prueba
                QueryEvent nextArrival = new QueryEvent(random.poisson(lambda),EventType.values()[0],null);
                addQueryEvent(nextArrival);
                // Server data is refreshed on the GUI: Servers status, queue information, clients served.
                userInterface.showServersInformation(clientAdministrator.getFreeServers(),processAdministrator.getFreeServers(),queryProcessor.getFreeServers(),queryExecutions.getFreeServers(),transactions.getFreeServers());
                userInterface.showLqInformation(processAdministrator.getQueueSize(), queryProcessor.getQueueSize(), queryExecutions.getQueueSize(), transactions.getPriorityQueueSize());
                userInterface.showServedConnectionsInformation(clientAdministrator.getNumClientsServed(), processAdministrator.getNumClientsServed(),queryProcessor.getNumClientsServed(),queryExecutions.getNumClientsServed(),transactions.getNumClientsServed());
                userInterface.showConnectionsInformation(this.getNumConectionServed(), this.getNumRejected());
            }
            // Every statistic is displayed at the end of the simulations inside the JTextArea
            userInterface.showTextinGUI("\n---- Estádisticas globales del sistema:\n");
            userInterface.showTextinGUI("Número de conexiones: "+ numConections);
            userInterface.showTextinGUI("Total de conexiones atendidas:" + numConectionServed);
            userInterface.showTextinGUI("Total de conexiones que hicieron timeout: "+ numTimeOut);
            userInterface.showTextinGUI("Total de conexiones rechazadas: "+ numRejected);

            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +statistics.getSelectAverageTime()+" num "+statistics.getNumSelect());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "    +statistics.getDdlAverageTime()+" num "+statistics.getNumDdl());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "   +statistics.getJoinAverageTime()+" num "+statistics.getNumJoin());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: " +statistics.getUpdateAverageTime()+" num "+statistics.getNumUdpate());

            userInterface.showTextinGUI("\n---- Estádisticas de cada módulo:\n");
            userInterface.showTextinGUI("Módulo: Administración de Clientes");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +clientAdministrator.getStatistic().getSelectAverageTime()+" para "+clientAdministrator.getStatistic().getNumSelect() + " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +clientAdministrator.getStatistic().getDdlAverageTime()+" para "+clientAdministrator.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+clientAdministrator.getStatistic().getJoinAverageTime()+" para "+clientAdministrator.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+clientAdministrator.getStatistic().getUpdateAverageTime()+" para "+clientAdministrator.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+clientAdministrator.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));

            userInterface.showTextinGUI("Lambda: " +clientAdministrator.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +clientAdministrator.getStatistic().getP(clientAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+clientAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+clientAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +clientAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("W: " +clientAdministrator.getStatistic().getW());
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
            userInterface.showTextinGUI("Ws: "+processAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+processAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +processAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("W: " +processAdministrator.getStatistic().getW());
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
            userInterface.showTextinGUI("Ws: "+queryExecutions.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+queryExecutions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +queryExecutions.getStatistic().getU());
            userInterface.showTextinGUI("W: " +queryExecutions.getStatistic().getW());
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
            userInterface.showTextinGUI("Ws: "+queryProcessor.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+queryProcessor.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +queryProcessor.getStatistic().getU());
            userInterface.showTextinGUI("W: " +queryProcessor.getStatistic().getW());
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
            userInterface.showTextinGUI("Ws: "+transactions.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+transactions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +transactions.getStatistic().getU());
            userInterface.showTextinGUI("W: " +transactions.getStatistic().getW());
            userInterface.showTextinGUI("L: "+transactions.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+transactions.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+transactions.getStatistic().getLq());

            userInterface.activateReturnButton();
            //se crea html con estadisticas
            generateHTML(i+1);

        }
        //File htmlFile = new File("/statistics/");
        //Desktop.getDesktop().browse(htmlFile.toURI());
    }

    /**
     * procesa el primer elemento de la lista de eventos
     */

    public boolean checkTimeOut(Connection c){
        boolean time_out = false;
        if(c.getArrivalTime()+timeOut<= clock){
            QueryEvent event = new QueryEvent(clock, EventType.values()[2], c);
            addQueryEvent(event);
            time_out = true;
        }
        return time_out;
    }

    public void procesEvent(){
        QueryEvent actualEvent = this.getNextEvent();

        clock =  actualEvent.getEventTime();
        //se procesa segun el tipo de evento

        if(slowMode) {
            userInterface.showTextinGUI("\nEvento actual: " + actualEvent.getType());
        }
        userInterface.showActualEvent(actualEvent.getType());

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
                    newConnection.setArrivalTime(clock);
                    double serviceTime = processAdministrator.generateServiceTime();
                    //userInterface.showTextinGUI("\nllegada: " + (serviceTime+clock));
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

                if(slowMode) {
                    userInterface.showTextinGUI("\nMódulo actual: "  + actualModule);
                }
                userInterface.showActualModule("" + actualModule);
                //userInterface.showTextinGUI("modulo: " + actualModule.getModule());
                // si ya paso el tiempo de servicio se crea un evento de tipo time out
                boolean processing;
                //se busca el modulo en el que esta la conexion
                switch (actualModule.getModule()) {
                    case "CLIENT_ADMIN":
                        // se revisa si la conexion ya paso por el modulo de transacciones

                        if (checkTimeOut(actualConnection) == false) {
                            //if (!actualConnection.getTransactionModule()) {
                                //aun no pasa por el modulo de transacciones
                                processing = processAdministrator.arrive(actualConnection, clock); // el proceso llega el siguente modulo
                                if (processing == true) {
                                    // si es atendido
                                    //se calcula el tiempo de servicio  y se actualiza la variable del modulo actual, se actualizan las estadisticas
                                    // y se añade el evento a la lista de eventos
                                    double serviceTime = processAdministrator.generateServiceTime();
                                    actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                                    processAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                                    QueryEvent event = new QueryEvent((clock + serviceTime), EventType.values()[3], actualConnection);
                                    addQueryEvent(event);
                                }
                                //ya paso por el modulo de transacciones
                            //} else {
                                // se saca el proceso del modulo
                                //se crea un evento de tipo connection_out
                                //QueryEvent event = new QueryEvent(clock, EventType.values()[1], actualConnection);
                                //addQueryEvent(event);
                           // }
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
                            client_p.setCurrentModule(ModuleFlag.values()[1]);
                            processAdministrator.updateStatistics(client_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_p);
                            addQueryEvent(event);
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
                                addQueryEvent(event);
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
                            client_q_p.setCurrentModule(ModuleFlag.values()[2]);
                            queryProcessor.updateStatistics(client_q_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_q_p);
                            addQueryEvent(event);
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
                                addQueryEvent(event);
                            }
                        }
                        break;

                    case "TRANSACTION":
                        //do {
                            Connection client_t = transactions.exit(clock);
                            if (client_t != null) {
                                double diskBloks = transactions.loadDiskBloks(actualConnection.getType().toString());
                                client_t.setBlocksRead(diskBloks);
                                double serviceTime = transactions.generateServiceTime(diskBloks);
                                client_t.setCurrentModule(ModuleFlag.values()[3]);
                                transactions.updateStatistics(client_t, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_t);
                                addQueryEvent(event);
                            }
                       // }while (transactions.getFreeServers()<2 && transactions.getFreeServers()>0 && transactions.getServingDDL()==false && transactions.getPriorityQueueSize()>0 );

                        actualConnection.setTransactionModuleTrue();
                        if (checkTimeOut(actualConnection) == false) {
                            processing = queryExecutions.arrive(actualConnection, clock);
                            if (processing == true) {
                                double serviceTime = queryExecutions.generateServiceTime(actualConnection.getBlocksRead(), actualConnection.getType().toString());
                                actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                queryExecutions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                addQueryEvent(event);
                            }
                        }
                        break;

                    case "QUERY_EXE":

                        Connection client_q = queryExecutions.exit(clock);
                        if (client_q != null) {
                            double serviceTime = queryExecutions.generateServiceTime(client_q.getBlocksRead(), client_q.getType().toString());
                            client_q.setCurrentModule(ModuleFlag.values()[4]);
                            client_q.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                            queryExecutions.updateStatistics(client_q, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_q);
                            addQueryEvent(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            //regresa al modulo de administracion de clientes
                            double serviceTime = actualConnection.getBlocksRead() / 2;
                            actualConnection.setCurrentModule(ModuleFlag.values()[0]);
                            clientAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[1], actualConnection);
                            addQueryEvent(event);
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


    public int getNumSimulations() {
        return numSimulations;
    }

    public void generateHTMLindex(int simulations) throws Exception {

        File dir = new File("Statistics");
        dir.mkdir();

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("/src/Index.vm");
        Vector<String> v=new Vector<String>();

        for(int i=1; i<=getNumSimulations(); i++) {
            v.add("Simulacion" + Integer.toString(i));
        }

        VelocityContext vc = new VelocityContext();
        vc.put("List", v);


        StringWriter sw = new StringWriter();
        t.merge(vc, sw);

//        System.out.println(sw);
        File file = new File("Statistics/Index.html");
        FileWriter fw = new FileWriter(file);
        fw.write(sw.toString());
        fw.close();
    }

    public void generateHTML(int currentSim) throws Exception {

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("/src/simulation.vm");
        //Vector<String> v=new Vector<String>();

        VelocityContext vc = new VelocityContext();

        vc.put("name", "Simulacion"+Integer.toString(currentSim));
        vc.put("simtime", getSecondsSimulation());
        vc.put("slowmode", getSlowModeSeconds());
        vc.put("k", clientAdministrator.getMaxSimConnections());
        vc.put("n", queryProcessor.getMaxSimConnections());
        vc.put("p", transactions.getMaxSimConnections());
        vc.put("m", queryExecutions.getMaxSimConnections());
        vc.put("t", getTimeOut());
        vc.put("connections", getNumConections());
        vc.put("served", getNumConectionServed());
        vc.put("timeout", getNumTimeOut());
        vc.put("rejected", getNumRejected());
        vc.put("w", statistics.getW());
        vc.put("lqc", clientAdministrator.getStatistic().getLq());
        vc.put("lqqp", queryProcessor.getStatistic().getLq());
        vc.put("lqt", transactions.getStatistic().getLq());
        vc.put("lqqe", queryExecutions.getStatistic().getLq());
        vc.put("lqp", processAdministrator.getStatistic().getLq());

        vc.put("lambda1", clientAdministrator.getStatistic().getLambda());
        vc.put("lambda2", processAdministrator.getStatistic().getLambda());
        vc.put("lambda3", queryExecutions.getStatistic().getLambda());
        vc.put("lambda4", queryProcessor.getStatistic().getLambda());
        vc.put("lambda5", transactions.getStatistic().getLambda());

        vc.put("mu1", clientAdministrator.getStatistic().getU());
        vc.put("mu2", processAdministrator.getStatistic().getU());
        vc.put("mu3", queryExecutions.getStatistic().getU());
        vc.put("mu4", queryProcessor.getStatistic().getU());
        vc.put("mu5", transactions.getStatistic().getU());

        vc.put("rho1", clientAdministrator.getStatistic().getP(clientAdministrator.getMaxSimConnections()));
        vc.put("rho2", processAdministrator.getStatistic().getP(processAdministrator.getMaxSimConnections()));
        vc.put("rho3", queryExecutions.getStatistic().getP(queryExecutions.getMaxSimConnections()));
        vc.put("rho4", queryProcessor.getStatistic().getP(queryProcessor.getMaxSimConnections()));
        vc.put("rho5", transactions.getStatistic().getP(transactions.getMaxSimConnections()));


        vc.put("L1", clientAdministrator.getStatistic().getL());
        vc.put("L2", processAdministrator.getStatistic().getL());
        vc.put("L3", queryExecutions.getStatistic().getL());
        vc.put("L4", queryProcessor.getStatistic().getL());
        vc.put("L5", transactions.getStatistic().getL());

        vc.put("Ls1", clientAdministrator.getStatistic().getLs());
        vc.put("Ls2", processAdministrator.getStatistic().getLs());
        vc.put("Ls3", queryExecutions.getStatistic().getLs());
        vc.put("Ls4", queryProcessor.getStatistic().getLs());
        vc.put("Ls5", transactions.getStatistic().getLs());

        vc.put("Lq1", clientAdministrator.getStatistic().getLq());
        vc.put("Lq2", processAdministrator.getStatistic().getLq());
        vc.put("Lq3", queryExecutions.getStatistic().getLq());
        vc.put("Lq4", queryProcessor.getStatistic().getLq());
        vc.put("Lq5", transactions.getStatistic().getLq());

        vc.put("W", statistics.getW());

        vc.put("W1", clientAdministrator.getStatistic().getW());
        vc.put("W2", processAdministrator.getStatistic().getW());
        vc.put("W3", queryExecutions.getStatistic().getW());
        vc.put("W4", queryProcessor.getStatistic().getW());
        vc.put("W5", transactions.getStatistic().getW());

        vc.put("Wq1", clientAdministrator.getStatistic().getStackAverageTime());
        vc.put("Wq2", processAdministrator.getStatistic().getStackAverageTime());
        vc.put("Wq3", queryExecutions.getStatistic().getStackAverageTime());
        vc.put("Wq4", queryProcessor.getStatistic().getStackAverageTime());
        vc.put("Wq5", transactions.getStatistic().getStackAverageTime());

        vc.put("Ws1", clientAdministrator.getStatistic().getWs());
        vc.put("Whs2", processAdministrator.getStatistic().getWs());
        vc.put("Ws3", queryExecutions.getStatistic().getWs());
        vc.put("Ws4", queryProcessor.getStatistic().getWs());
        vc.put("Ws5", transactions.getStatistic().getWs());

        vc.put("lazy1", clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));
        vc.put("lazy2", processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));
        vc.put("lazy3", queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));
        vc.put("lazy4", queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));
        vc.put("lazy5", transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));

        //  vc.put("active1", 1-clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));
        // vc.put("active2", 1-processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));
        // vc.put("active3", 1-queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));
        /// vc.put("active4", 1-queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));
        /// vc.put("active5", 1-transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));


        vc.put("ddl", statistics.getDdlAverageTime());
        vc.put("join", statistics.getJoinAverageTime());
        vc.put("select", statistics.getSelectAverageTime());
        vc.put("update", statistics.getUpdateAverageTime());

        StringWriter sw = new StringWriter();
        t.merge(vc, sw);

        //      System.out.println(sw);
        File file = new File("Statistics/Simulacion"+Integer.toString(currentSim)+".html");
        FileWriter fw = new FileWriter(file);
        fw.write(sw.toString());
        fw.close();
    }

    public int getNumConections() {
        return numConections;
    }

    public int getNumConectionServed() {
        return numConectionServed;
    }

    public int getNumTimeOut() {
        return numTimeOut;
    }

    public int getNumRejected() {
        return numRejected;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public int getSecondsSimulation() {
        return secondsSimulation;
    }

    public int getSlowModeSeconds() {
        return slowModeSeconds;
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
            if(query1.getEventTime() > query2.getEventTime()){
                priority = 1;
            }else{
                priority = -1;
            }
            return priority;
        }
    }
}

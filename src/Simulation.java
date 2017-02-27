import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

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


    public Simulation(int numSims, int secsSim, boolean slowMode, int slowModeSecs){
        this.setClock(0); // Inicializamos el reloj en el tiempo 0
        this.setNumSimulations(numSims); // Se guarda el número de simulaciones que se van a realizar
        this.setSlowMode(slowMode); // Se guarda la bandera del Slow Mode
        this.setSecondsSimulation(slowModeSecs); // Se guardan los segundos del delay para el Slow Mode

        Comparator<QueryEvent> comparator = new QueryComparator(); // Creamos el comparador que utiliza la cola de prioridades
        PriorityQueue<QueryEvent> stack = new PriorityQueue<QueryEvent>(comparator); // Instanciamos la cola de prioridades con el
        setStackQueries(stack);


        clock=0;

        numSimulations = numSims;
        secondsSimulation = secsSim;
        this.slowMode = slowMode;
        slowModeSeconds = slowModeSecs;
        beginSimulation();
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

    public void beginSimulation(){
        for(int i=0; i<numSimulations; i++) {
            //para las llegadas agrego una conexion de tipo nulo

            // Creamos los objetos específicos de cada módulo con el cual se comunica Simulation
            this.clientAdministrator = new ClientAdministratorModule();
            this.processAdministrator = new ProcessAdministratorModule();
            this.queryExecutions = new QueryExecutionsModule();
            this.queryProcessor = new QueryProcessorModule();
            this.transactions = new TransactionsModule();
            this.statistics = new StatisticsModule();

            RandomGenerator random = new RandomGenerator();
            clock = 0;
            eventList.clear();
            QueryEvent firstArrival = new QueryEvent(0,EventType.values()[0],null);
            eventList.add(firstArrival);

            //prueba
            System.out.println("simulacion "+i);
            //prueba

            while(secondsSimulation > clock) {
                procesEvent();

                //prueba
                System.out.println("relog "+clock);
                //prueba
                QueryEvent nextArrival = new QueryEvent(random.poisson(lambda),EventType.values()[0],null);
                eventList.add(nextArrival);
                //se actualiza la interfaz y las estadisticas;
            }
            System.out.println("conexiones "+numConections);
            System.out.println("conexiones atendidas "+numConectionServed);
            System.out.println("time out "+numTimeOut);
            System.out.println("rechazadas "+numRejected);

            System.out.println("promedio select "+statistics.getSelectAverageTime()+" num "+statistics.getNumSelect());
            System.out.println("promedio ddl "+statistics.getDdlAverageTime()+" num "+statistics.getNumDdl());
            System.out.println("promedio join "+statistics.getJoinAverageTime()+" num "+statistics.getNumJoin());
            System.out.println("promedio update "+statistics.getUpdateAverageTime()+" num "+statistics.getNumSelect());

            System.out.println("clientAdministrator ");
            System.out.println("promedio select "+clientAdministrator.getStatistic().getSelectAverageTime()+" num "+clientAdministrator.getStatistic().getNumSelect());
            System.out.println("promedio ddl "+clientAdministrator.getStatistic().getDdlAverageTime()+" num "+clientAdministrator.getStatistic().getNumDdl());
            System.out.println("promedio join "+clientAdministrator.getStatistic().getJoinAverageTime()+" num "+clientAdministrator.getStatistic().getNumJoin());
            System.out.println("promedio update "+clientAdministrator.getStatistic().getUpdateAverageTime()+" num "+clientAdministrator.getStatistic().getNumSelect());

            System.out.println("processAdministrator ");
            System.out.println("promedio select "+processAdministrator.getStatistic().getSelectAverageTime()+" num "+processAdministrator.getStatistic().getNumSelect());
            System.out.println("promedio ddl "+processAdministrator.getStatistic().getDdlAverageTime()+" num "+processAdministrator.getStatistic().getNumDdl());
            System.out.println("promedio join "+processAdministrator.getStatistic().getJoinAverageTime()+" num "+processAdministrator.getStatistic().getNumJoin());
            System.out.println("promedio update "+processAdministrator.getStatistic().getUpdateAverageTime()+" num "+processAdministrator.getStatistic().getNumSelect());

            System.out.println("queryExecutions ");
            System.out.println("promedio select "+queryExecutions.getStatistic().getSelectAverageTime()+" num "+queryExecutions.getStatistic().getNumSelect());
            System.out.println("promedio ddl "+queryExecutions.getStatistic().getDdlAverageTime()+" num "+queryExecutions.getStatistic().getNumDdl());
            System.out.println("promedio join "+queryExecutions.getStatistic().getJoinAverageTime()+" num "+queryExecutions.getStatistic().getNumJoin());
            System.out.println("promedio update "+queryExecutions.getStatistic().getUpdateAverageTime()+" num "+queryExecutions.getStatistic().getNumSelect());

            System.out.println("queryProcessor ");
            System.out.println("promedio select "+queryProcessor.getStatistic().getSelectAverageTime()+" num "+queryProcessor.getStatistic().getNumSelect());
            System.out.println("promedio ddl "+queryProcessor.getStatistic().getDdlAverageTime()+" num "+queryProcessor.getStatistic().getNumDdl());
            System.out.println("promedio join "+queryProcessor.getStatistic().getJoinAverageTime()+" num "+queryProcessor.getStatistic().getNumJoin());
            System.out.println("promedio update "+queryProcessor.getStatistic().getUpdateAverageTime()+" num "+queryProcessor.getStatistic().getNumSelect());

            System.out.println("transactions ");
            System.out.println("promedio select "+transactions.getStatistic().getSelectAverageTime()+" num "+transactions.getStatistic().getNumSelect());
            System.out.println("promedio ddl "+transactions.getStatistic().getDdlAverageTime()+" num "+transactions.getStatistic().getNumDdl());
            System.out.println("promedio join "+transactions.getStatistic().getJoinAverageTime()+" num "+transactions.getStatistic().getNumJoin());
            System.out.println("promedio update "+transactions.getStatistic().getUpdateAverageTime()+" num "+transactions.getStatistic().getNumSelect());

            //se crea html con estadisticas
        }
    }

    /**
     * procesa el primer elemento de la lista de eventos
     */

    public void procesEvent(){
        QueryEvent actualEvent = this.getNextEvent();

        clock =  actualEvent.getEventTime();
        //se procesa segun el tipo de evento

        //prueba
        System.out.println(actualEvent.getType());
        //prueba


        switch (actualEvent.getType()){
            case "CONNECTION_IN":
                numConections++;
                if(!clientAdministrator.checkMaxConnections()){ // Se revisa si el Client Administrator tiene servidores libres o no
                    clientAdministrator.rejectConnection(); // Si no tiene servidores libres se rechaza la conexión
                    numRejected++;
                    //prueba
                    System.out.println("rechaza conexion");
                    //prueba

                } else { // Si hay servidores libres, se crea la conexión

                    //prueba
                    System.out.println("recibe conexion");
                    //prueba

                    Connection newConnection = clientAdministrator.createConnection();
                    newConnection.setCurrentModule(ModuleFlag.values()[0]);
                    clientAdministrator.arrive(newConnection, clock);
                    newConnection.setType();
                    double serviceTime = processAdministrator.generateServiceTime(0, newConnection.getType().getReadOnly(), newConnection.getType().toString());
                    clientAdministrator.updateStatistics(newConnection, serviceTime, clock);
                    QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], newConnection);
                    eventList.add(event);
                }
                // falta definir como se va a trabajar esta parte.
                break;
            case "CONNECTION_OUT":
                numConectionServed++;
                Connection out = actualEvent.getConnection();
                updateStatistics(out.getType().toString(), out.getArrivalTime());
                //prueba
                System.out.println("termina conexion");
                //prueba
                //statistics.setStatistics(actualEvent); //la clase statisticsModule aun no tiene metodos
                break;
            case "TIME_OUT":
                numTimeOut++;
                //prueba
                System.out.println("termina conexion(tiempo)");
                //prueba
                //no se que hay que hacer con el time_out, hay alguna estadistica de esto?
                break;
            case "EXIT_MODULE":
                Connection actualConnection = actualEvent.getConnection();
                ModuleFlag actualModule = actualConnection.getCurrentModule(); // se busca el modulo actual
                // si ya paso el tiempo de servicio se crea un evento de tipo time out
                if(actualConnection.getArrivalTime()+timeOut<= clock){
                    QueryEvent event = new QueryEvent(clock, EventType.values()[2], actualConnection);
                    eventList.add(event);
                }
                else{
                    boolean processing;
                    switch (actualModule.getModule()) {

                        case "CLIENT_ADMIN":

                            //prueba
                            System.out.println("sale conexion(client_Admin)");
                            //prueba

                            if (!actualConnection.getTransactionModule()) {
                                processing = processAdministrator.arrive(actualConnection, clock); // el proceso llega el siguente modulo
                                // si es atendido se calcula el tiempo de servicio
                                if (processing == true) {
                                    //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                    double serviceTime = processAdministrator.generateServiceTime(1, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                    actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                                    processAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                                    QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                                    eventList.add(event);
                                } else {
                                    //estadisticas de procesos rechazados
                                }
                            }else{
                                // se saca el proceso del modulo
                                //la conexion termina cuando se cargan los bloques del disco
                                Connection client_a = clientAdministrator.exit();
                                QueryEvent event = new QueryEvent(clock, EventType.values()[1], actualConnection);
                                eventList.add(event);
                            }
                            break;

                        case "PROCESS_ADMIN":

                            //prueba
                            System.out.println("sale conexion(proces_Admin)");
                            //prueba

                            Connection client_p = processAdministrator.exit();
                            if (client_p != null) {
                                double serviceTime = processAdministrator.generateServiceTime(1, client_p.getType().getReadOnly(), client_p.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                                processAdministrator.updateStatistics(client_p, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], client_p);
                                eventList.add(event);
                            }
                            processing = queryProcessor.arrive(actualConnection, clock);
                            if (processing == true) {
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                double serviceTime = queryProcessor.generateServiceTime(2, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                actualConnection.setCurrentModule(ModuleFlag.values()[2]);
                                queryProcessor.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                            break;

                        case "QUERY_PROCESSOR":

                            //prueba
                            System.out.println("sale conexion(queryProcessor)");
                            //prueba

                            Connection client_q_p = queryProcessor.exit();
                            if (client_q_p != null) {
                                double serviceTime = queryProcessor.generateServiceTime(2, client_q_p.getType().getReadOnly(), client_q_p.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                actualConnection.setCurrentModule(ModuleFlag.values()[2]);
                                queryProcessor.updateStatistics(client_q_p, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], client_q_p);
                                eventList.add(event);
                            }

                            processing = transactions.arrive(actualConnection, clock);
                            if (processing == true) {
                                //Cada transaccion va a tomar un tiempo p ∗ 0.03 segundos en coordinar la ejecucion de una transaccion (p es el nunero de procesos concurrentes).
                                double serviceTime = (transactions.getMaxSimConnections()-transactions.getFreeServers())*.03;
                                //transactions es el modulo encargado de leer los bloques del disco
                                double diskBloks = transactions.generateServiceTime(3, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                actualConnection.setBlocksRead(diskBloks);
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                actualConnection.setCurrentModule(ModuleFlag.values()[3]);
                                //cada bloque del disco ocupa 1/10 segundos para ser leido
                                serviceTime += diskBloks/10;
                                transactions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                            break;

                        case "TRANSACTION":
                            //prueba
                            System.out.println("sale conexion(transaction)");
                            //prueba

                            Connection client_t = transactions.exit();
                            if (client_t != null) {
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                //Cada transaccion va a tomar un tiempo p ∗ 0.03 segundos en coordinar la ejecucion de una transaccion (p es el nunero de procesos concurrentes).
                                double serviceTime = (transactions.getMaxSimConnections()-transactions.getFreeServers())*.03;
                                //transactions es el modulo encargado de leer los bloques del disco
                                double diskBloks = transactions.generateServiceTime(3, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                actualConnection.setBlocksRead(diskBloks);
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                client_t.setCurrentModule(ModuleFlag.values()[3]);
                                //cada bloque del disco ocupa 1/10 segundos para ser leido
                                serviceTime += diskBloks/10;
                                client_t.setCurrentModule(ModuleFlag.values()[3]);
                                transactions.updateStatistics(client_t, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], client_t);
                                eventList.add(event);
                            }

                            // se pasa al true el booleano que indica que ya paso por el modulo de transacciones
                            actualConnection.setTransactionModuleTrue();
                            //segunda entrada a queryProcesor (en el enunciado luego dice ejecutor de consultas asi que lo estoy enviando a queryExecutions )
                            processing = queryExecutions.arrive(actualConnection, clock);
                            if (processing == true) {
                                double serviceTime = Math.pow(actualConnection.getBlocksRead(), 2) / 1000; // este tiempo es en milisegundos
                                if(actualConnection.getType().toString()=="UPDATE"){
                                    serviceTime = 1;
                                }else{
                                    if(actualConnection.getType().toString()=="DDL"){
                                        serviceTime = 0.5;
                                    }
                                }

                                actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                queryExecutions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }
                            break;

                        case "QUERY_EXE":

                            //prueba
                            System.out.println("sale conexion(query_Exe)");
                            //prueba

                            Connection client_q = queryExecutions.exit();
                            if (client_q != null) {
                                double serviceTime = Math.pow(actualConnection.getBlocksRead(), 2) / 1000; // este tiempo es en milisegundos
                                if(actualConnection.getType().toString()=="UPDATE"){
                                    serviceTime = 1;
                                }else{
                                    if(actualConnection.getType().toString()=="DDL"){
                                        serviceTime = 0.5;
                                    }
                                }

                                actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                queryExecutions.updateStatistics(client_q, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                                eventList.add(event);
                            }

                            //regresa al modulo de administracion de clientes
                            double serviceTime = actualConnection.getBlocksRead()/2;
                            actualConnection.setCurrentModule(ModuleFlag.values()[0]);
                            clientAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock+serviceTime, EventType.values()[3], actualConnection);
                            eventList.add(event);
                            break;
                    }
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
        Simulation simulation = new Simulation(numSim, secSim,slowMode,slowModeSecs);

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

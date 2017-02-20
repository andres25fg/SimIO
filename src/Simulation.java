import java.util.Deque;

/**
 * Clase Simualtion
 *
 * Esta clase contiene la definición de la clase principal de la simulación que se encarga de comunicarse con las demás clases del sistema
 *
 */

public class Simulation  {
    private double clock; // Reloj del sistema
    private boolean slowMode; // Booleano para saber si la simulación se va a hacer en modo lento
    private int slowModeSeconds; // Segundos de la simulación para el modo lento
    private int numSimulations; // Número de veces que se va a realizar la simulación
    private int secondsSimulation; // Segundos para la simulación normal
    private int timeOut; // Segundos que tiene una conexion para ser atendida
    private ClientAdministratorModule clientAdministrator; // Client Administrator
    private ProcessAdministratorModule processAdministrator; // Process Administrator
    private QueryExecutionsModule queryExecutions; // Query Exections
    private QueryProcessorModule queryProcessor; // Query Processor
    private TransactionsModule transactions; // Transactions
    private Deque<QueryEvent> eventList; // Lista de eventos del sistema
    public StatisticsModule statistics;


    public Simulation(int numSims, int secsSim, boolean slowMode, int slowModeSecs){
        this.setClock(0); // Inicializamos el reloj en el tiempo 0
        this.setNumSimulations(numSims); // Se guarda el número de simulaciones que se van a realizar
        this.setSlowMode(slowMode); // Se guarda la bandera del Slow Mode
        this.setSecondsSimulation(slowModeSecs); // Se guardan los segundos del delay para el Slow Mode

        // Creamos los objetos específicos de cada módulo con el cual se comunica Simulation
        this.clientAdministrator = new ClientAdministratorModule();
        this.processAdministrator = new ProcessAdministratorModule();
        this.queryExecutions = new QueryExecutionsModule();
        this.queryProcessor = new QueryProcessorModule();
        this.transactions = new TransactionsModule();
        this.statistics = new StatisticsModule();
    }

    /**
     * Método que agrega un QueryEvent a la cola de eventos
     * @param newEvent
     */
    private void addQueryEvent(QueryEvent newEvent) {
        eventList.add(newEvent);
    }

    /**
     * Método que saca el siguiente evento de la cola y lo elimina de la cola
     * @return QueryEvent evento que se encuentra en la cabeza de la cola de eventos.
     */
    private QueryEvent getNextEvent() {
        return eventList.pop();
    }

    public void beginSimulation(){
        while(numSimulations > 0) {
            while(secondsSimulation > 0) {

            }
        }
    }

    /**
     * procesa el primer elemento de la lista de eventos
     */

    public void procesEvent(){
        boolean endConnection = false; //false = la conexion aun no termina, true terminar conexion
        QueryEvent actualEvent = this.getNextEvent();
        clock =  actualEvent.getEventTime();
        //se procesa segun el tipo de evento
        switch (actualEvent.toString()){
            case "CONNECTION_IN":
                if(!clientAdministrator.checkMaxConnections()){ // Se revisa si el Client Administrator tiene servidores libres o no
                    clientAdministrator.rejectConnection(); // Si no tiene servidores libres se rechaza la conexión
                } else { // Si hay servidores libres, se crea la conexión
                    Connection newConnection = clientAdministrator.createConnection();
                    clientAdministrator.arrive(newConnection);
                }
                // falta definir como se va a trabajar esta parte.
                break;
            case "CONNECTION_OUT":
                endConnection=true;
                //statistics.setStatistics(actualEvent); //la clase statisticsModule aun no tiene metodos
                break;
            case "TIME_OUT":
                endConnection= true;
                //no se que hay que hacer con el time_out, hay alguna estadistica de esto?
                break;
            case "EXIT_MODULE":
                Connection actualConnection = actualEvent.getConnection();
                ModuleFlag actualModule = actualConnection.getCurrentModule(); // se busca el modulo actual
                // si ya paso el tiempo de servicio se crea un evento de tipo time out
                if(actualConnection.getArrivalTime()+timeOut>= clock){
                    QueryEvent event = new QueryEvent(clock, EventType.values()[3], actualConnection);
                    eventList.add(event);
                }
                else{
                    boolean processing;
                    switch (actualModule.toString()) {

                        case "CLIENT_ADMIN":
                            Connection client_a = clientAdministrator.exit(); // se saca el proceso del modulo
                            // si hay un proceso en espera este es atendido
                            if (client_a != null) {
                                double serviceTime = clientAdministrator.generateServiceTime(1, client_a.getType().getReadOnly(), client_a.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                //no se como ponerle el tipo de enum a cada evento, tal vez enviando un parametro extra al constructor de QueryEvent o revisando el moduleFlag (pero creo que
                                //esto seria mas lento)
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                                eventList.add(event);
                            }
                            if (!actualConnection.getTransactionModule()) {
                                processing = processAdministrator.arrive(actualConnection); // el proceso llega el siguente modulo
                                // si es atendido se calcula el tiempo de servicio
                                if (processing == true) {
                                    //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                    double serviceTime = processAdministrator.generateServiceTime(2, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                    QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                                } else {
                                    QueryEvent event = new QueryEvent(actualEvent.getEventTime(), EventType.values()[2], client_a);
                                }
                            }
                            break;

                        case "PROCESS_ADMIN":
                            Connection client_p = processAdministrator.exit();
                            if (client_p != null) {
                                double serviceTime = processAdministrator.generateServiceTime(2, client_p.getType().getReadOnly(), client_p.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_p);
                                eventList.add(event);
                            }
                            processing = queryExecutions.arrive(actualConnection);
                            if (processing == true) {
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                double serviceTime = queryExecutions.generateServiceTime(3, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], actualConnection);
                                ;
                                eventList.add(event);
                            }
                            break;

                        case "QUERY_EXE":
                            Connection client_q = queryExecutions.exit();
                            if (client_q != null) {
                                double serviceTime = queryProcessor.generateServiceTime(3, client_q.getType().getReadOnly(), client_q.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_q);
                                eventList.add(event);
                            }
                            processing = transactions.arrive(actualConnection);
                            if (processing == true) {
                                double serviceTime = transactions.generateServiceTime(4, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], actualConnection);
                                eventList.add(event);
                            }
                            break;

                        case "QUERY_PROCESSOR":
                            Connection client_q_p = queryProcessor.exit();
                            if (client_q_p != null) {
                                double serviceTime = queryProcessor.generateServiceTime(4, client_q_p.getType().getReadOnly(), client_q_p.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_q_p);
                                eventList.add(event);
                            }

                            if (!actualConnection.getTransactionModule()) {
                                processing = transactions.arrive(actualConnection);
                                if (processing == true) {
                                    double serviceTime = transactions.generateServiceTime(5, actualConnection.getType().getReadOnly(), actualConnection.getType().toString());
                                    //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                    QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], actualConnection);
                                    eventList.add(event);
                                }
                            } else {
                                //segundo ingreso al modulo de administrador de clientes
                                processing = clientAdministrator.arrive(actualConnection);
                                if (processing == true) {
                                    double serviceTime = actualConnection.getBlocksRead() / 2;
                                    //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                    QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], actualConnection);
                                    eventList.add(event);
                                }
                            }
                            break;

                        case "TRANSACTION":
                            Connection client_t = transactions.exit();
                            if (client_t != null) {
                                double serviceTime = transactions.generateServiceTime(5, client_t.getType().getReadOnly(), client_t.getType().toString());
                                //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                                //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_t);
                                eventList.add(event);
                            }
                            actualConnection.setTransactionModuleTrue();
                            //segunda entrada a queryProcesor
                            processing = queryProcessor.arrive(actualConnection);
                            if (processing == true) {
                                double serviceTime = Math.pow(actualConnection.getBlocksRead(), 2) / 1000; // este tiempo es en milisegundos
                                //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], actualConnection);
                                eventList.add(event);
                            }
                            break;
                    }
                }
                break;
            }
        if(endConnection== false){
            //se agrega un nuevo elemento a eventList con la conexion que estamos trabajando excepto para exit y time_out;
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

    public static void main (String[] args){
        //QueryEvent queryE = new QueryEvent();

        /*
        Agregar evento de entrada en el tiempo 0
        while(clock < TIEMPO DE SIMULACION){
            processEvent();
         */
        /*for (QueryType i : new QueryType[] { QueryType.DDL, QueryType.UPDATE, QueryType.JOIN, QueryType.SELECT}) {
            System.out.println(i.toString() + " " + i.getPriority() + " " + i.getReadOnly());
        }*/
    }
}

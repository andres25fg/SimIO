import java.util.Deque;

/**
 * Clase Simualtion
 *
 * Esta clase contiene la definición de la clase principal de la simulación que se encarga de comunicarse con las demás clases del sistema
 *
 */

public class Simulation  {
    private int clock; // Reloj del sistema
    private boolean slowMode; // Booleano para saber si la simulación se va a hacer en modo lento
    private int slowModeSeconds; // Segundos de la simulación para el modo lento
    private int numSimulations; // Número de veces que se va a realizar la simulación
    private int secondsSimulation; // Segundos para la simulación normal
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
    //procesa el primer elemento de la cola
    public void ProcesEvent(){
        boolean endConnection = false; //false = la conexion aun no termina, true terminar conexion
        QueryEvent actualEvent = this.getNextEvent();
            clock =  actualEvent.getEventTime();
            //se procesa segun el tipo de evento
        switch ("Prueba CAMBIAR"){//HAY QUE CAMBIAR ESTO
            case "CONNECTION_IN":
                clientAdministrator.creeateConnection();
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
                boolean processing;
                switch (actualModule.toString()){
                    case "CLIENT_ADMIN":
                        Connection client_a = clientAdministrator.exit();
                        if(client_a!= null){
                            int serviceTime = clientAdministrator.generateServiceTime(0);
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            //no se como ponerle el tipo de enum a cada evento, tal vez enviando un parametro extra al constructor de QueryEvent o revisando el moduleFlag (pero creo que
                            //esto seria mas lento)
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                            eventList.add(event);
                        }
                        processing = processAdministrator.arrive(client_a);
                        if(processing==true) {
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            int serviceTime = clientAdministrator.generateServiceTime(0);
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                        }
                        break;
                    case "PROCESS_ADMIN":
                        Connection client_p = processAdministrator.exit();
                        if(client_p!= null){
                            int serviceTime = processAdministrator.generateServiceTime(0);
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_p);
                            eventList.add(event);
                        }
                        processing = queryExecutions.arrive(client_p);
                        if(processing==true) {
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            int serviceTime = queryExecutions.generateServiceTime(0);
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);;
                            eventList.add(event);
                        }
                        break;
                    case "QUERY_EXE":
                        Connection client_q = queryExecutions.exit();
                        if(client_q!= null){
                            int serviceTime = queryExecutions.generateServiceTime(0);
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                            eventList.add(event);
                        }
                        processing = transactions.arrive(client_q);
                        if(processing==true) {
                            int serviceTime = transactions.generateServiceTime(0);
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                            eventList.add(event);
                        }
                        break;
                    case "TRANSACTION":
                        Connection client_t = transactions.exit();
                        if (client_t != null) {
                            int serviceTime = transactions.generateServiceTime(0);
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                            eventList.add(event);
                        }
                        processing = transactions.arrive(client_t);
                                if (processing == true) {
                                    int serviceTime = transactions.generateServiceTime(0);
                                    //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                                    QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                                    eventList.add(event);
                                }
                                    break;
                    case "QUERY_PROCESSOR":
                        Connection client_q_p = queryProcessor.exit();
                        if (client_q_p != null) {
                            int serviceTime = queryProcessor.generateServiceTime(0);
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new QueryEvent(serviceTime, EventType.values()[4], client_a);
                            eventList.add(event);
                        }
                        break;
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

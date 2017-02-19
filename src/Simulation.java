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
    }

    /**
     * Método que agrega un QueryEvent a la cola de eventos
     * @param newEvent
     */
    private void addQueryEvent(QueryEvent newEvent) {
        eventList.add(newEvent);
    }

    /**
     * Método que saca el siguiente evento de la cola
     * @return QueryEvent evento que se encuentra en la cabeza de la cola de eventos. Lo saca de la cola
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
   /* public void ProcesEvent(){
        boolean endConnection = false; //false = la conexion aun no termina, true terminar conexion
        QueryEvent actualEvent = this.getNextEvent();
            clock =  actualEvent.getTime();
            //se procesa segun el tipo de evento
        switch (tipo de evento){
            case "CONNECTION_IN":
                ClientAdministratorModule();
                break;
            case "CONNECTION_OUT":
                endConnection=true;
                setStatistics(actualEvent);
                break;
            case "TIME_OUT":
                endConnection= true;
                no se que hay que hacer con el time_out, hay alguna estadistica de esto?
                break;
            case "EXIT_MODULE":
                module = QueryEvent.getConecction().getCurrentModule(); // se busca el modulo actual
                switch (tipo de evento){
                    case "CLIENT_ADMIN":
                        Connection client_a = ClientAdministratorModule.exit();
                        if(client_a!= null){
                            int serviceTime = ClientAdministratorModule.generateServiceTime();
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            //no se como ponerle el tipo de enum a cada evento, tal vez enviando un parametro extra al constructor de QueryEvent o revisando el moduleFlag (pero creo que
                            //esto seria mas lento)
                            QueryEvent event = new Event(client_a);
                            eventList.addQueryEvent(event, serviceTime);
                        }
                        boolean processing = ProcessAdministratorModule.arrive();
                        if(processing==true){
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            int serviceTime = ClientAdministratorModule.generateServiceTime()
                            QueryEvent event = new Event(actualEvent, serviceTime);
                        break;
                    case "PROCESS_ADMIN":
                        Connection client_p = ProcessAdministratorModule.exit();
                        if(client_p!= null){
                            int serviceTime = ProcessAdministratorModule.generateServiceTime()
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new Event(client_p);
                            eventList.addQueryEvent(event, serviceTime);
                        }
                        boolean processing = QueryExecutionsModule.arrive();
                        if(processing==true){
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            int serviceTime = ProcessAdministratorModule.generateServiceTime();
                            QueryEvent event = new Event(actualEvent, serviceTime);
                        break;
                    case "QUERY_EXE":
                        Connection client_q = QueryExecutionsModule.exit();
                        if(client_q!= null){
                            int serviceTime = QueryExecutionsModule.generateServiceTime();
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new Event(client_q);
                            eventList.addQueryEvent(event, serviceTime);
                        }
                        boolean processing = TransactionsModule.arrive();
                        if(processing==true){
                            int serviceTime = QueryExecutionsModule.generateServiceTime();
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            QueryEvent event = new Event(actualEvent, serviceTime);
                        break;
                    case "TRANSACTION":
                       Connection client_t = TransactionsModule.exit();
                       if(client_t!= null){
                            int serviceTime = TransactionsModule.generateServiceTime();
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new Event(client_t);
                            eventList.addQueryEvent(event, serviceTime);
                        }
                        boolean processing = QueryProcessorModule.arrive();
                        if(processing==true){
                            int serviceTime = TransactionsModule.generateServiceTime();
                            //arrive podria devolver un booleano (verdadero si es atendido y falso si se envia a la cola)
                            QueryEvent event = new Event(actualEvent, serviceTime);
                        break;
                    case "QUERY_PROCESSOR":
                        Connection client_q_p = QueryProcessorModule.Exit();
                        if(client_q_p!= null){
                            int serviceTime = QueryProcessorModule.generateServiceTime(),
                            //creo que exit deberia retornar una conexion (la que acaba de salir de la lista del modulo) o null si la lista esta vacia
                            //si es distinta de null se crea un nuevo evento y se agrega a la lista de eventos
                            QueryEvent event = new Event(client_q_p);
                            eventList.addQueryEvent(event, serviceTime);
                        }

                        break;
                break;
            }

        }
        if(endConnection== false){
            se agrega un nuevo elemento a eventList con la conexion que estamos trabajando excepto para exit y time_out;
        }
    }*/

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
            QueryEvent queryE = new QueryEvent();
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

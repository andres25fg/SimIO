import java.util.PriorityQueue;

/**
 * Clase Simualtion
 *
 * Esta clase contiene la definición de la clase principal de la simulación que se encarga de comunicarse con las demás clases del sistema
 *
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
    private PriorityQueue<QueryEvent> eventList; // Lista de eventos del sistema


    public Simulation(){

    }



    public void beginSimulation(){

    }
    //procesa el primer elemento de la pila
   /* public void ProcesEvent(){
        boolean endConnection = false; //false = la conexion aun no termina, true terminar conexion
        actualEvent = saca evento de la cola();
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
                        ProcessAdministratorModule.arrive();
                        break;
                    case "PROCESS_ADMIN":
                        QueryExecutionsModule.arrive();
                        break;
                    case "QUERY_EXE":
                        TransactionsModule.arrive();
                        break;
                    case "TRANSACTION":
                       QueryProcessorModule().arrive();
                        break;
                    case "QUERY_PROCESSOR":
                        no se que va aqui pasa a otro modulo o le ponemos que salga?
                        break;
                break;
            }

        }
        if(endConnection== false){
            se agrega un nuevo elemento a eventList con la conexion que estamos trabajando excepto para exit y time_out;
        }
    }*/

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

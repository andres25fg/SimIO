import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by felipe on 17/2/2017.
 */
abstract class Module {
    private StatisticsModule statistics; // Objeto de la clase StatisticsModule para guardar estadísticas
    private int freeServers; // Número de servidores libres dle módulo
    private int maxSimConnections; // Número máximo de conexiones simultaneas que le módulo puede procesar
    private RandomGenerator random; // Objeto de la clase RandomGenerator para el procesamiento de los números aleatorios
    private int numClientsServed; // Número total de clientes servidos por el módulo
    private Deque<Connection> stackConnections; // Cola de conexiones del módulo
    private PriorityQueue<QueryType> stackQueries; // Cola de consultas

    public Module(){
    }

    public void setFreeServers(int freeServers) {
        this.freeServers = freeServers;
    }

    public void setMaxSimConnections(int maxSimConnections) {
        this.maxSimConnections = maxSimConnections;
    }

    public void setNumClientsServed(int numClientsServed) {
        this.numClientsServed = numClientsServed;
    }

    public int getFreeServers() {
        return freeServers;
    }

    public int getMaxSimConnections() {
        return maxSimConnections;
    }

    public void sendToStack(Connection c) { stackConnections.add(c);}

    public void reduceFreeServer() { freeServers--;}

    //este metodo se puede cambair en las clases hijas dependiendo de como se ocupe generar el service time
    public int generateServiceTime(int proba){
        return proba;
    }

    // En este metodo no estoy my seguro e como manejar la lista de eventos
    public void arrive(Connection c) {
        if(getFreeServers()==0) {
            sendToStack(c);
        }else{
            //el cliente pasa a servicio entonces el servidor pasa a estar ocupado
            reduceFreeServer();


        }
    }
    public void exit(Connection c) {

    }


}

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Created by felipe on 17/2/2017.
 */
public class AbstractModule {
    private StatisticsModule statistics; // Objeto de la clase StatisticsModule para guardar estadísticas
    private int freeServers; // Número de servidores libres dle módulo
    private int maxSimConnections; // Número máximo de conexiones simultaneas que le módulo puede procesar
    private RandomGenerator random; // Objeto de la clase RandomGenerator para el procesamiento de los números aleatorios
    private int numClientsServed; // Número total de clientes servidos por el módulo
    private ArrayDeque<Connection> stackConnections; // Cola de conexiones del módulo
    private PriorityQueue<QueryType> stackQueries; // Cola de consultas

    public AbstractModule(){
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

    public int getNumClientsServed() {
        return numClientsServed;
    }
}

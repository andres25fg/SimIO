/**
 * Clase TransactionsModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class TransactionsModule extends Module{

    /**
     * Constructor
     */
    public TransactionsModule(int servers) {
        this.setFreeServers(servers);
        Comparator<Connection> comparator = new QueryComparator(); // Creamos el comparador que utiliza la cola de prioridades
        PriorityQueue<Connection> stack = new PriorityQueue<Connection>(comparator); // Instanciamos la cola de prioridades con el comparador
        super.setStackQueries(stack); //
    }

    public double loadDiskBloks(String type){
        double blocks=0;
        if (type == "JOIN") {
            blocks += random.uniform(1, 16) + random.uniform(1, 12);
        } else {
            if (type == "SELECT") {
                blocks += random.uniform(1, 64);
            }
        }
        return blocks;
    }

    public double generateServiceTime(double blocks) {
        double time = (getMaxSimConnections()-getFreeServers())*.03;
        time += blocks/10;
        return time;
    }

    /**
     * Método que agrega una consulta a la cola de prioridades
     * @param query
     */
    public void addToQueryStack(Connection query) {
        super.getStackQueries().add(query);
    }

    /**
     * Método que devuelve la siguiente consulta de la cola
     * @return Connection
     */
    public Connection getNextQuery() {
        return super.getStackQueries().poll();
    }
    /**
     * Clase anidada que se utiliza para definir el comparador entre conexiones, para la cola de prioridad del módulo Transactions
     */
    public class QueryComparator implements Comparator<Connection> {
        /**
         * Método que realiza la comparación de prioridades de la cola
         * @param query1
         * @param query2
         * @return
         */
        public int compare(Connection query1, Connection query2) {
            //Hace una resta entre las prioridades de cada conexión para determinar el orden
            return query1.getType().getPriority() - query2.getType().getPriority();
        }
    }
}

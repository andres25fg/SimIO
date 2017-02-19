import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by felipe on 17/2/2017.
 */
public class TransactionsModule extends Module{

    /**
     * Constructor
     */
    public TransactionsModule() {
        Comparator<Connection> comparator = new QueryComparator(); // Creamos el comparador que utiliza la cola de prioridades
        PriorityQueue<Connection> stack = new PriorityQueue<Connection>(comparator); // Instanciamos la cola de prioridades con el comparador
        super.setStackQueries(stack); //
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

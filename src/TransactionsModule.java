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
    private boolean servingDDL=false;
    private int numDDL=0;
    public TransactionsModule(int servers) {
        this.setFreeServers(servers);
        this.setMaxSimConnections(servers);
        Comparator<Connection> comparator = new QueryComparator(); // Creamos el comparador que utiliza la cola de prioridades
        PriorityQueue<Connection> stack = new PriorityQueue<Connection>(comparator); // Instanciamos la cola de prioridades con el comparador
        super.setStackQueries(stack); //
    }

    public void arriveDDL(){
        servingDDL=true;
    }

    public void exitDDL(){
        servingDDL = false;
    }
    public boolean getServingDDL(){
        return servingDDL;
    }

    public boolean arrive(Connection c, double clock) {
        getStatistic().setLambda(clock-getTimeLastArrive());
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        setTimeLastArrive(clock);
        boolean being_served=false;

        //se revisa si la conexion es de tipo ddl
        if((getFreeServers()>0 && numDDL ==0 && servingDDL==false && c.getType().toString() != "DDL" )|| (getFreeServers() == getMaxSimConnections() ) ) {
            reduceFreeServer();
            being_served=true;
            if(c.getType().toString() == "DDL"){
                //si es de tipo ddl se incrementa el contador de ddl
                arriveDDL();
            }

        }else{
            //el cliente pasa a servicio entonces el servidor pasa a estar ocupado
            sendToQuery(c);
            c.setStack(true);
            c.setStackArrivalTime(clock);
            if(c.getType().toString() == "DDL"){
                //si es de tipo ddl se incrementa el contador de ddl
                numDDL++;
            }
        }
        return being_served;
    }

    public int getNumDDl(){
        return numDDL;
    }

    public Connection exit(double clock) {
        freeOneServer();
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        Connection next = null;
        exitDDL();
        if (getPriorityQueueSize() > 0) {
            if (numDDL == 0) {
                next = getFirstPriorityQueue();
                reduceFreeServer();
            } else {
                if(getFreeServers()==getMaxSimConnections()) {
                    numDDL--;
                    next = getFirstPriorityQueue();
                    reduceFreeServer();
                    arriveDDL();
                }
            }
        }
        return next;
    }

    public double loadDiskBloks(String type){
        double blocks=0;
        if (type == "JOIN") {
            blocks += getRandom().uniform(1, 16) + getRandom().uniform(1, 12);
        } else {
            if (type == "SELECT") {
                blocks += getRandom().uniform(1, 64);
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

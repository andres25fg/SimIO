import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Clase Module
 *
 * Esta clase abstracta contiene la definición de atributos y métodos base que se utilizan en los módulos principales dle sistema.
 * Estos modulos heredan herdan la superclase Module.
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public abstract class Module {
    private StatisticsModule statistics = new StatisticsModule(); // Objeto de la clase StatisticsModule para guardar estadísticas
    public int freeServers; // Número de servidores libres del módulo
    public int maxSimConnections; // Número máximo de conexiones simultaneas que le módulo puede procesar
    public RandomGenerator random = new RandomGenerator(); // Objeto de la clase RandomGenerator para el procesamiento de los números aleatorios
    private int numClientsServed; // Número total de clientes servidos por el módulo
    private Deque<Connection> stackConnections = new ArrayDeque<Connection>(); // Cola de conexiones del módulo
    public PriorityQueue<Connection> stackQueries; // Cola de consultas que utiliza el módulo de Transactions

    public double timeLastArrive=0;
    public double timeLastEvent=0;

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

    public int getNumClientsServed() {
        return numClientsServed;
    }

    public int getFreeServers() {
        return freeServers;
    }

    public int getMaxSimConnections() {
        return maxSimConnections;
    }

    public void setStackQueries(PriorityQueue<Connection> stackQueries) {
        this.stackQueries = stackQueries;
    }

    public PriorityQueue<Connection> getStackQueries() {
        return stackQueries;
    }

    /**
     * Método que retorna el número de conexiones que hay dentro de la cola de prioridades
     * @return
     */
    public int getPriorityQueueSize() {
        return stackQueries.size();
    }

    /**
     * Method that returns the Queue's number of conecctions waiting
     * @return returns the Queue's size,
     */
    public int getQueueSize() {
        return stackConnections.size();
    }

    /**
     * Método que agrega una conexión a la cola
     * @param c: Conexión que se va agregar a la cola
     */
    public void sendToStack(Connection c) { stackConnections.add(c);}

    /**
     * Método que reduce el número de servidores libres del módulo
     */
    public void reduceFreeServer() { freeServers--;}

    /**
     * Método que libera un servidor del módulo
     */
    public void freeOneServer() { freeServers++;}

    /**
     * metodo para calcular el tiempo de servicio en cada modulo
     * @param : permite conocer para cual modulo se calcula el tiempo de servicio (1:administracion de clientes,
     * 2 : administracion de procesos, 3: procesamiento de consultas, 4: transaccionesá)
     * @return
     */


    /*public double generateServiceTime(){
    }*/

    // En este metodo no estoy my seguro e como manejar la lista de eventos
    public boolean arrive(Connection c, double clock) {
        statistics.setLambda(clock-timeLastArrive);
        statistics.setFreeServersAndFreeTime(freeServers,(clock-timeLastEvent));
        timeLastEvent = clock;
        timeLastArrive = clock;
        boolean being_served=false;
        if(getFreeServers()==0) {
            sendToStack(c);
            c.setStack(true);
            c.setStackArrivalTime(clock);
        }else{
            //el cliente pasa a servicio entonces el servidor pasa a estar ocupado
            reduceFreeServer();
            being_served=true;
        }
        return being_served;
    }

    public Connection exit(double clock) {
        freeOneServer();
        statistics.setFreeServersAndFreeTime(freeServers,(clock-timeLastEvent));
        timeLastEvent = clock;
        Connection next;
        if(stackConnections.isEmpty()!=true){
            next = stackConnections.pollFirst();
            reduceFreeServer();
        }else{
            next=null;
        }
        return next;
    }
    /**
     *
     * @param c conexion que va a ser atendida
     * @param serviceTime tiempo de servicio de la conexion
     * @param clock hora actual del sistema
     */
    public void updateStatistics(Connection c, double serviceTime, double clock){
        // se revisa si la conexion entro a la cola del modulo
        double stackTime =0;
        if(c.getStack()){
            //si entro a la cola al tiempo de servicio se le suma el tiempo que paso en la cola y se coloca
            stackTime += clock-c.getStackArrivalTime();
            c.setStack(false);
            statistics.setStackAverageTime(stackTime);
        }
        statistics.setWs(serviceTime);
        switch (c.getType().toString()){
            case "DDL":
                statistics.setDdlAverageTime(stackTime+serviceTime);
                break;
            case "UPDATE":
                statistics.setUpdateAverageTime(stackTime+serviceTime);
                break;
            case "JOIN":
                statistics.setJoinAverageTime(stackTime+serviceTime);
                break;
            case  "SELECT":
                statistics.setSelectAverageTime(stackTime+serviceTime);
        }
    }

    public StatisticsModule getStatistic(){
        return statistics;
    }


}

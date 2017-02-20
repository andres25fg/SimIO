import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by felipe on 17/2/2017.
 */
public abstract class Module {
    private StatisticsModule statistics; // Objeto de la clase StatisticsModule para guardar estadísticas
    private int freeServers; // Número de servidores libres dle módulo
    private int maxSimConnections; // Número máximo de conexiones simultaneas que le módulo puede procesar
    private RandomGenerator random; // Objeto de la clase RandomGenerator para el procesamiento de los números aleatorios
    private int numClientsServed; // Número total de clientes servidos por el módulo
    private Deque<Connection> stackConnections; // Cola de conexiones del módulo
    private PriorityQueue<Connection> stackQueries; // Cola de consultas que utiliza el módulo de Transactions

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

    public void setStackQueries(PriorityQueue<Connection> stackQueries) {
        this.stackQueries = stackQueries;
    }

    public PriorityQueue<Connection> getStackQueries() {
        return stackQueries;
    }

    /**
     * Método que agrega una conexión a la cola
     * @param c
     */
    public void sendToStack(Connection c) { stackConnections.add(c);}

    public void reduceFreeServer() { freeServers--;}


    /**
     * metodo para calcular el tiempo de servicio en cada modulo
     * @param module: permite conocer para cual modulo se calcula el tiempo de servicio (1:administracion de clientes,
     * 2 : administracion de procesos, 3: procesamiento de consultas, 4: transacciones)
     * @return
     */
    public double generateServiceTime(int module, boolean readOnly, String type){
        double time=0;
        switch (module) {
            case 1: // administracion de clientes
                time = random.uniform(0.01, 0.05);
                break;
            case 2: //administracion de procesos
                time = random.normal(1.5, 0.1);
                break;
            case 3: //procesamiento de consultas
                double rand = random.getRandom(); //validacion lexica
                if (rand < 0.7) {
                    time += 0.1;
                } else {
                    time += 0.4;
                }
                time += random.uniform(0, 0.8);//validacion sintactica
                time += random.normal(1, 0.5); // validacion  semantica
                time += random.exponential(.7);//verificacion de permisos
                if (readOnly) { // optimizacion de consultas
                    time += 0.1;
                } else {
                    time += 0.5;
                }
                break;
            case 4: // transacciones
                if (type == "JOIN") {
                    time += random.uniform(1, 16) + random.uniform(1, 12);
                } else {
                    if (type == "SELECT") {
                        time += random.uniform(1, 64);
                    }
                }
                time = time / 10;
                break;
        }
        return time;
    }

    // En este metodo no estoy my seguro e como manejar la lista de eventos
    public boolean arrive(Connection c) {
        boolean being_served=false;
        if(getFreeServers()==0) {
            sendToStack(c);
        }else{
            //el cliente pasa a servicio entonces el servidor pasa a estar ocupado
            reduceFreeServer();
            being_served=true;
        }
        return being_served;
    }
    public Connection exit() {
        reduceFreeServer();
        Connection next;
        if(stackConnections.isEmpty()!=true){
            next = stackConnections.getFirst();
        }else{
            next=null;
        }
        return next;
    }


}

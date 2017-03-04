/**
 * Class ClientAdministratorModule
 *
 * Extends from the abstract class: Module
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class ClientAdministratorModule extends Module {
    private int rejectedConnections; // Number of rejected connections

    /**
     * Constructor
     * @param connection: receives the maximum number of connections, and sets the number of servers available
     */
    public ClientAdministratorModule(int connection) {
        this.setMaxSimConnections(connection);
        this.setFreeServers(connection);
    }

    /**
     * Creates a new connection in the system
     * @return
     */
    public Connection createConnection(){
        Connection c;
        c = new Connection();
        return c;
    }

    /**
     * Checks if the maximum number of simultaneous connections has been reached
     * @return boolean: returns true if there are servers available, or false if there are none
     */
    public boolean checkMaxConnections() {
       int servers = super.getFreeServers();
       boolean check = true;
       if(servers == 0) {
           check = false;
       }
       return check;
    }

    /**
     * Receives a connection and the module checks if the connection can use an available server. If not, it goes
     * @param c
     * @param clock
     * @return
     */
    public boolean arrive(Connection c, double clock) {
        getStatistic().setLambda(clock-getTimeLastArrive());
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        setTimeLastArrive (clock);
        boolean being_served=false;
        if(getFreeServers()==0) { // Checks if all servers are being used
            rejectConnection(); // If so, it rejects the new connection
        }else{
            // If there are servers available, the client starts using a server
            reduceFreeServer(); // the numbers of free servers is reduced because of the new client being attended
            being_served=true;
        }
        return being_served;
    }

    /**
     * Exits a connections frm the module
     * @param clock
     * @return
     */
    public Connection exit(double clock) {
        freeOneServer(); // the module frees one server because the connection is exiting the module
        incrementNumClientsServed(); // as the connection exits the module, the number of clients served is increased
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        return null;
    }

    /**
     * Increments the number of rejected connections
     */
    public void rejectConnection() {
        rejectedConnections++;
    }

    public void endConnection( int timeout){

    }

    /**
     * Generates the service time for this module. This module uses a uniform distribution
     * @return double: returns the generated service time
     */
    public double generateServiceTime(){
        return getRandom().uniform(0.01, 0.05);
    }

    public void updateStatistics(Connection c, double serviceTime, double clock){
        // se revisa si la conexion entro a la cola del modulo
        double stackTime =0;
        if(c.getStack()){
            //si entro a la cola al tiempo de servicio se le suma el tiempo que paso en la cola y se coloca
            stackTime += clock-c.getStackArrivalTime();
            c.setStack(false);
            getStatistic().setStackAverageTime(stackTime);
        }
        getStatistic().setWs(serviceTime);
        //el booleano se envia para saber si ya se paso por Transaction (las conexiones entran 2 veces a clientAdministrator) y asi no contar 2 veces cada conexion
        switch (c.getType().toString()){
            case "DDL":
                getStatistic().setDdlAverageTime(stackTime+serviceTime, c.getTransactionModule());
                break;
            case "UPDATE":
                getStatistic().setUpdateAverageTime(stackTime+serviceTime, c.getTransactionModule());
                break;
            case "JOIN":
                getStatistic().setJoinAverageTime(stackTime+serviceTime, c.getTransactionModule());
                break;
            case  "SELECT":
                getStatistic().setSelectAverageTime(stackTime+serviceTime, c.getTransactionModule());
        }
    }

}

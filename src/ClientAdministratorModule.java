/**
 * Clase ClientAdministratorModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class ClientAdministratorModule extends Module {
    private int rejectedConnections; // Número de conexiones rechazadas

    public ClientAdministratorModule(int connection) {
        this.setMaxSimConnections(connection);
        this.setFreeServers(connection);
    }

    public Connection createConnection(){
        Connection c;
        c = new Connection();
        return c;
    }

    /**
     * Método que revisa si el número de conexiones máximas simultaneas ha sido alcanzado
     * @return devuelve true en caso de tener servidores libres, y falso en el caso contrario
     */
    public boolean checkMaxConnections() {
       int servers = super.getFreeServers();
       boolean check = true;
       if(servers == 0) {
           check = false;
       }
       return check;
    }

    public boolean arrive(Connection c, double clock) {
        getStatistic().setLambda(clock-getTimeLastArrive());
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        setTimeLastArrive (clock);
        boolean being_served=false;
        if(getFreeServers()==0) {
            rejectConnection();
        }else{
            //el cliente pasa a servicio entonces el servidor pasa a estar ocupado
            reduceFreeServer();
            being_served=true;
        }
        return being_served;
    }

    public Connection exit(double clock) {
        freeOneServer();
        incrementNumClientsServed();
        getStatistic().setFreeServersAndFreeTime(getFreeServers(),(clock-getTimeLastEvent()));
        setTimeLastEvent(clock);
        return null;

    }

    /**
     * Aumenta la cantidad de conexiones rechazadas
     */
    public void rejectConnection() {
        rejectedConnections++;
    }

    public void endConnection( int timeout){

    }
    public double generateServiceTime(){
        return getRandom().uniform(0.01, 0.05);
    }

}

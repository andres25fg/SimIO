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

    /**
     * Aumenta la cantidad de conexiones rechazadas
     */
    public void rejectConnection() {
        rejectedConnections++;
    }

    public void endConnection( int timeout){

    }
    public double generateServiceTime(){
        return random.uniform(0.01, 0.05);
    }

}

/**
 * Class ProcessAdministratorModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class ProcessAdministratorModule extends Module {

    /**
     * Constructor
     * @param servers: number of servers and maximum simultaneous connections this module can handle
     */
    public ProcessAdministratorModule(int servers) {
        this.setFreeServers(servers);
        this.setMaxSimConnections(servers);
    }

    /**
     * Generates the service time for this module. It uses a normal distribution
     * @return
     */
    public double generateServiceTime(){
        return getRandom().normal(1.5, 0.1);
    }
}

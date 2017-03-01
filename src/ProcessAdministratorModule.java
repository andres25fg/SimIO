/**
 * Clase ProcessAdministratorModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class ProcessAdministratorModule extends Module {

    public ProcessAdministratorModule(int servers) {
        this.setFreeServers(servers);
    }

    public double generateServiceTime(){
        return random.normal(1.5, 0.1);
    }
}

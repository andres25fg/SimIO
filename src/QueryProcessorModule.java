/**
 * Clase QueryProcessorModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class QueryProcessorModule extends Module{
    public QueryProcessorModule(int servers) {
        this.setFreeServers(servers);
        this.setMaxSimConnections(servers);
    }

    public double generateServiceTime(boolean readOnly) {
        double time = 0;
        double rand = getRandom().getRandom(); //validacion lexica
        if (rand < 0.7) {
            time += 0.1;
        } else {
            time += 0.4;
        }
        time += getRandom().uniform(0, 0.8);//validacion sintactica
        time += getRandom().normal(1, 0.5); // validacion  semantica
        time += getRandom().exponential(.7);//verificacion de permisos
        if (readOnly) { // optimizacion de consultas
            time += 0.1;
        } else {
            time += 0.5;
        }
        return time;
    }
}

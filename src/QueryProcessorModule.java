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
        return time;
    }
}

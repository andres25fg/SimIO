/**
 * Class QueryProcessorModule
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

    /**
     * Generates the service time of this module
     * @param readOnly: boolean to know if the query is read only or not
     * @return
     */
    public double generateServiceTime(boolean readOnly) {
        double time = 0;
        double rand = getRandom().getRandom(); // Lexical validation
        if (rand < 0.7) {
            time += 0.1;
        } else {
            time += 0.4;
        }
        time += getRandom().uniform(0, 0.8);// Syntactic validation
        time += getRandom().normal(1, 0.5); // Semantic validation
        time += getRandom().exponential(.7);// Permits verification
        if (readOnly) { // Query optimization
            time += 0.1;
        } else {
            time += 0.5;
        }
        return time;
    }
}

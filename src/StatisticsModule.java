/**
 * Clase StatisticsModule
 *
 * Clase que define el objeto de StatisticsModule que se utiliza para guardar las estadísticas de cada módulo
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class StatisticsModule {
    private double stackAverageTime;// Variable para guardar el tiempo promedio en cola
    private double ddlAverageTome; // Tempo promedio de las consultas DDL
    private double selectAverageTome; // Tempo promedio de las consultas Join
    private double joinAverageTome; // Tempo promedio de las consultas Select
    private double uptdateAverageTome; // Tempo promedio de las consultas Update

    public StatisticsModule() {
    }
}

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

    public double getStackAverageTime() {
        return stackAverageTime;
    }

    public void setStackAverageTime(double stackAverageTime) {
        this.stackAverageTime = stackAverageTime;
    }

    public double getDdlAverageTome() {
        return ddlAverageTome;
    }

    public void setDdlAverageTome(double ddlAverageTome) {
        this.ddlAverageTome = ddlAverageTome;
    }

    public double getSelectAverageTome() {
        return selectAverageTome;
    }

    public void setSelectAverageTome(double selectAverageTome) {
        this.selectAverageTome = selectAverageTome;
    }

    public double getJoinAverageTome() {
        return joinAverageTome;
    }

    public void setJoinAverageTome(double joinAverageTome) {
        this.joinAverageTome = joinAverageTome;
    }

    public double getUptdateAverageTome() {
        return uptdateAverageTome;
    }

    public void setUptdateAverageTome(double uptdateAverageTome) {
        this.uptdateAverageTome = uptdateAverageTome;
    }

    public StatisticsModule() {
    }
}

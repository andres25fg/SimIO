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
    private double stackAverageTime=0;// Variable para guardar el tiempo promedio en cola
    private double numstack=0;
    private double ddlAverageTime=0; // Tempo promedio de las consultas DDL
    private double numDdl=0;
    private double selectAverageTime=0; // Tempo promedio de las consultas Join
    private double numSelect=0;
    private double joinAverageTime=0; // Tempo promedio de las consultas Select
    private double numJoin=0;
    private double uptdateAverageTime=0; // Tempo promedio de las consultas Update
    private double numUdpate=0;

    public double getStackAverageTime() {
        return stackAverageTime/numstack;
    }

    public void setStackAverageTime(double stackAverageTime) {
        this.stackAverageTime += stackAverageTime/numSelect;
        numstack++;
    }

    public double getDdlAverageTime() {
        return ddlAverageTime/numDdl;
    }

    public void setDdlAverageTime(double ddlAverageTime) {
        this.ddlAverageTime += ddlAverageTime;
        numDdl++;
    }
    public double getNumDdl(){
        return  numDdl;
    }

    public double getSelectAverageTime() {
        return selectAverageTime/numSelect;
    }

    public void setSelectAverageTime(double selectAverageTime) {
        this.selectAverageTime += selectAverageTime;
        numSelect++;
    }

    public double getNumSelect(){
        return  numSelect;
    }

    public double getJoinAverageTime() {
        return joinAverageTime/numSelect;
    }

    public void setJoinAverageTime(double joinAverageTime) {
        this.joinAverageTime += joinAverageTime;
        numJoin++;
    }

    public double getNumJoin(){
        return  numJoin;
    }

    public double getUpdateAverageTime() {
        return uptdateAverageTime/numJoin;

    }

    public void setUpdateAverageTime(double uptdateAverageTime) {
        this.uptdateAverageTime = uptdateAverageTime;
        numUdpate++;
    }
    public double getNumUdpate() {
        return numUdpate;
    }

    public StatisticsModule() {
    }
}

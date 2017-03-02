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
    private double stackAverageTime=0;// Variable para guardar el tiempo promedio en cola Wq
    private double numstack=0;
    private double ddlAverageTime=0; // Tempo promedio de las consultas DDL
    private double numDdl=0;
    private double selectAverageTime=0; // Tempo promedio de las consultas Join
    private double numSelect=0;
    private double joinAverageTime=0; // Tempo promedio de las consultas Select
    private double numJoin=0;
    private double uptdateAverageTime=0; // Tempo promedio de las consultas Update
    private double numUdpate=0;

    private double lambda =0;
    private double ws = 0;

    //se divinde la suma de los tiempos entre llegadas y se dividen entre el numero de llegadas para generar el tiempo entre arribos
    public double getLambda(){
        return  round(1/(lambda/(numDdl+numJoin+numSelect+numUdpate)));
    }
    //se suman los tiempos entre llegadas
    public void setLambda (double lambda){
        this.lambda += lambda;
    }

    public double getP (double numServers){
        return round(getLambda()/(numServers*(1/ws)));
    }

    public double getWs(){
        return  round(ws/(numDdl+numJoin+numSelect+numUdpate));
    }
    public void setWs (double ws){
        this.ws += ws;
    }

    public double getU(){
        return round(1/getWs());
    }

    public double getW(){
        return round(getWs()+getStackAverageTime());
    }

    public double getLs(){
        return round( getLambda()*getWs());
    }

    public double getLq(){
        return round(getLambda()*getStackAverageTime());
    }

    public double getL(){
        return round(getLambda()*(getWs()+getStackAverageTime()));
    }


    public double getStackAverageTime() {
        return round(stackAverageTime/numstack);
    }

    public void setStackAverageTime(double stackAverageTime) {
        this.stackAverageTime += stackAverageTime;
        numstack++;
    }

    public double getDdlAverageTime() {
        return round(ddlAverageTime/numDdl);
    }

    public void setDdlAverageTime(double ddlAverageTime) {
        this.ddlAverageTime += ddlAverageTime;
        numDdl++;
    }
    public double getNumDdl(){
        return  numDdl;
    }

    public double getSelectAverageTime() {
        return round(selectAverageTime/numSelect);
    }

    public void setSelectAverageTime(double selectAverageTime) {
        this.selectAverageTime += selectAverageTime;
        numSelect++;
    }

    public double getNumSelect(){
        return  numSelect;
    }

    public double getJoinAverageTime() {
        return round(joinAverageTime/numJoin);
    }

    public void setJoinAverageTime(double joinAverageTime) {
        this.joinAverageTime += joinAverageTime;
        numJoin++;
    }

    public double getNumJoin(){
        return  numJoin;
    }

    public double getUpdateAverageTime() {
        return round(uptdateAverageTime/numUdpate);

    }

    public void setUpdateAverageTime(double uptdateAverageTime) {
        this.uptdateAverageTime = uptdateAverageTime;
        numUdpate++;
    }
    public double getNumUdpate() {
        return numUdpate;
    }

    public double round(double number){
        number = Math.round(number*1000);
        return number/1000;
    }

    public StatisticsModule() {
    }
}

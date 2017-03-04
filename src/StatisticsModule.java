/**
 * Class StatisticsModule
 *
 * This class defines the object that contains all the statistics required for a module. All modules use this class
 * to save each of their statistics
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class StatisticsModule {
    private double stackAverageTime=0;// Variable for saving the average time in the queue -> Wq
    private int numstack=0;
    private double ddlAverageTime=0; // Average time of DDL queries
    private int numDdl=0; // Number of DDL queries
    private double selectAverageTime=0; // Average time of SELECT queries
    private int numSelect=0; // Number of SELECT queries
    private double joinAverageTime=0; // Average time of JOIN queries
    private int numJoin=0; // Number of JOIN queries
    private double uptdateAverageTime=0; // Average time of UPDATE queries
    private int numUdpate=0; // Number of UPDATE queries
    private double moduleFreeTime =0; // Free time the module spends

    private double lambda =0;
    private double ws = 0;

    private int numFreeServers = 0; // Free servers are added
    private double serversFreeTime = 0; // Sum of the free time of the servers
    private double timesFreeServers = 0; // Number of times free servers are added

    public double getMododuleFreeTime(){
        return round(moduleFreeTime);
    }

    public void setModuleFreeTime(double time){
        moduleFreeTime +=time;
    }
    public double getServersFreeTime() {
        return serversFreeTime;
    }


    /**
     * Division of the free servers by the number of times the free servers are added
     * @return
     */
    public double getAverageFreeServers(){
        return  round(numFreeServers/timesFreeServers);
    }

    /**
     * Divides the sum of the servers free time by the number of servers to get the average free time per server
     * @param numServers
     * @return
     */
    public double getAverageFreeTime(double numServers){
        return  round(serversFreeTime/numServers);
    }

    public void setFreeServersAndFreeTime(int freeServers, double freeTime){
        numFreeServers +=freeServers;
        serversFreeTime += (freeTime*freeServers);
        timesFreeServers++;
    }

    // time between arrivals is divided by the number of arrivals of all type of queries to generate the time between arrivals
    public double getLambda(){
        return  round(1/(lambda/(numDdl+numJoin+numSelect+numUdpate)));
    }
    //Time between arrivals are added
    public void setLambda (double lambda){
        this.lambda += lambda;
    }


    public double getP (double numServers){
        return round((getLambda()/(numServers*getU())));
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

    public void setDdlAverageTime(double ddlAverageTime, boolean transactionModule) {
        this.ddlAverageTime += ddlAverageTime;
        if(transactionModule == false){
            numDdl++;
        }

    }
    public int getNumDdl(){
        return  numDdl;
    }

    public double getSelectAverageTime() {
        return round(selectAverageTime/numSelect);
    }

    public void setSelectAverageTime(double selectAverageTime, boolean transactionModule) {
        this.selectAverageTime += selectAverageTime;
        if(transactionModule == false) {
            numSelect++;
        }
    }

    public int getNumSelect(){
        return  numSelect;
    }

    public double getJoinAverageTime() {
        return round(joinAverageTime/numJoin);
    }

    public void setJoinAverageTime(double joinAverageTime, boolean transactionModule) {
        this.joinAverageTime += joinAverageTime;
        if(transactionModule == false) {
            numJoin++;
        }
    }

    public int getNumJoin(){
        return  numJoin;
    }

    public double getUpdateAverageTime() {
        return round(uptdateAverageTime/numUdpate);

    }

    // The boolean transactionsModule is used to determined if a connection is passing through the Client Administrator for the second time.
    // For the other modules, a false is always send as a parameter
    public void setUpdateAverageTime(double uptdateAverageTime, boolean transactionModule) {
        this.uptdateAverageTime += uptdateAverageTime;
        if(transactionModule == false) {
            numUdpate++;
        }
    }
    public int getNumUdpate() {
        return numUdpate;
    }

    /**
     * Rounds the number to three decimals
     * @param number
     * @return
     */
    public double round(double number){
        number = Math.round(number*1000);
        return number/1000;
    }

    public StatisticsModule() {
    }
}

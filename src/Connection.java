import java.util.Random;

/**
 * Class Connection
 *
 * Contains the definition of the connections used in the system
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class Connection {
    private QueryType type; /// Type of query of the connection. DDL, UPDATE, JOIN, SELECT.
    private ModuleFlag currentModule;/// Connection's current module. Saves where whe connection is at the current moment
    private int disckBlocks;// Disk block's size
    private int result;// Result of the query
    private double arrivalTime;// Arrival time to the system
    private double stackArrivalTime=0; //Time when the connection got pushed in the Queue of the current module
    private boolean stack = false; // Flag that shows if the connection got into the Queue.
    boolean transactionModule=false; // Falg to know if a connections has already passed through the transactions module
    double blocksRead;

    /**
     * Constructor
     */
    public Connection() {
    }

    /**
     * Constructor
     * @param type: type of Query
     * @param arrivalTime: arrival time to the system
     */
    public Connection(QueryType type, int arrivalTime) {
        this.type = type;
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public QueryType getType() {
        return type;
    }

    /**
     * Sets the type of query the Connection will have. Uses a random generated number to determined the type of query
     */
    public void setType() {
        double r =new Random().nextDouble();
        if(r>=0.00&&r<0.32){
            type=QueryType.SELECT;
        }else if(r>=0.32&&r<0.60){
            type=QueryType.UPDATE;
        }else if(r>=0.60&&r<0.93){
            type=QueryType.JOIN;
        }else if(r>=0.93&&r<1){
            type=QueryType.DDL;
        }
    }

    public ModuleFlag getCurrentModule() {
        return currentModule;
    }

    public void setCurrentModule(ModuleFlag currentModule) {
        this.currentModule = currentModule;
    }

    public int getDisckBlocks() {
        return disckBlocks;
    }

    public void setDisckBlocks(int disckBlocks) {
        this.disckBlocks = disckBlocks;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setTransactionModuleTrue(){
        transactionModule = true;
    }
    public boolean getTransactionModule(){
        return transactionModule;
    }
    public void setBlocksRead(double b){
        blocksRead = b;
    }

    public double getBlocksRead() {
        return blocksRead;
    }
    public void setStackArrivalTime(double time){
        stackArrivalTime = time;
    }
    public double getStackArrivalTime(){
        return stackArrivalTime;
    }
    public void setStack (boolean stack){
        this.stack = stack;
    }
    public boolean getStack(){
        return stack;
    }
}

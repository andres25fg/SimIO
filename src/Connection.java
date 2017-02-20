import java.util.Random;

/**
 * Created by felipe on 17/2/2017.
 */
public class Connection {
    private QueryType type; /// Tipo de consulta DDL, JOIN ETC
    private ModuleFlag currentModule;/// Modulo actual en el q se encuentra la conexion
    private int disckBlocks;//tamaño del bloque de disco
    private int result;//Resultado de la consulta
    private int arrivalTime;//Tiempo de llegada al sistema

    boolean transactionModule=false; // permite conocer si la conexion ya paso por el modulo de transacciones, despues
    double blocksRead;
    // es atendido por algunos modulos por segunda vez

    public Connection() {
    }

    public Connection(QueryType type, int arrivalTime) {
        this.type = type;
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public QueryType getType() {
        return type;
    }

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
}

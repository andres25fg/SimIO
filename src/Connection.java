/**
 * Created by felipe on 17/2/2017.
 */
public class Connection {
    private QueryType type; /// Tipo de consulta DDL, JOIN ETC
    private ModuleFlag currentModule;/// Modulo actual en el q se encuentra la conexion
    private int disckBlocks;//tamaño del bloque de disco
    private int result;//Resultado de la consulta
    private int arrivalTime;//Tiempo de llegada al sistema

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

    public void setType(QueryType type) {
        this.type = type;
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






}

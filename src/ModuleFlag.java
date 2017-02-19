/**
 * Created by felipe on 17/2/2017.
 */
public enum ModuleFlag {
    CLIENT_ADMIN("Curent ModuleI is Client Administrator"),
    PROCESS_ADMIN("Curent ModuleI is Process Administrator"),
    QUERY_EXE("Curent ModuleI is Query Executor"),
    TRANSACTION("Curent ModuleI is Transactions"),
    QUERY_PROCESSOR("Current ModuleI Query Processor");


    private String msj;
    ModuleFlag(String s) {
        this.msj=s;
    }


}

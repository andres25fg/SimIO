/**
 * Created by felipe on 17/2/2017.
 */
public enum ModuleFlag {
    PROCESS_ADMIN("Curent ModuleI is Process Administrator"),
    QUERY_ADMIN("Curent ModuleI is Query Administrator"),
    TRANSACTION("Curent ModuleI is Transactions"),
    QUERY_PROCESSOR("Current ModuleI Query Processor"),
    CLIENT_ADMIN("Curent ModuleI is Client Administrator");

    private String msj;
    ModuleFlag(String s) {
        this.msj=s;
    }


}

/**
 * Enumeration ModuleFlag
 *
 * Enumeration que define los tipos de módulos. De manera que se pueda utilizar como banderas para revisar en donde se encuentra la conexión
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
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

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
    CLIENT_ADMIN("Current Module is Client Administrator"),
    PROCESS_ADMIN("Current Module is Process Administrator"),
    QUERY_EXE("Current Module is Query Executor"),
    TRANSACTION("Current Module is Transactions"),
    QUERY_PROCESSOR("Current Module Query Processor");


    private String msj;
    ModuleFlag(String s) {
        this.msj=s;
    }


}

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
    CLIENT_ADMIN("CLIENT_ADMIN"),
    PROCESS_ADMIN("PROCESS_ADMIN"),
    QUERY_PROCESSOR("QUERY_PROCESSOR"),
    TRANSACTION("TRANSACTION"),
    QUERY_EXE("QUERY_EXE");



    private String msj;
    ModuleFlag(String s) {
        this.msj=s;
    }

    public String getModule(){
        return msj;
    }


}

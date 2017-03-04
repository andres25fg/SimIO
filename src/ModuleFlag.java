/**
 * Enumeration ModuleFlag
 *
 * Enumeration that defines all possible module types.
 * This allows to know where the connection is in. Serves as a flag so the Simulation can know in which module the connection is at the moment
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

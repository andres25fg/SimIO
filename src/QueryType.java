/**
 * Enumeration QueryType
 *
 * Define los tipos de consultas que pueden procesarse en el sistema
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public enum QueryType {
    DDL(1, false),
    UPDATE(2, false),
    JOIN(3, true),
    SELECT(4, true);


    private boolean readOnly;
    private int priority;
    QueryType(int priority, boolean readOnly) {
        this.priority = priority;
        this.readOnly = readOnly;
    }
    public int getPriority(){
        return priority;
    }
    public boolean getReadOnly(){
        return readOnly;
    }
}
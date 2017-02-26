/**
 * Enumeration EventType
 *
 * Enumeration que define los diferentes tipos de eventos que se procesan en la simulación
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
 enum EventType {
    CONNECTION_IN("CONNECTION_IN"),
    CONNECTION_OUT("CONNECTION_OUT"),
    TIME_OUT("TIME_OUT"),
    EXIT_MODULE("EXIT_MODULE");

    private String type;

    EventType(String type){
        this.type=type;
    }

    public String getType(){
        return type;
    }
}

/**
 * Clase QueryEvent
 *
 * Clase que define el objeto evento
 */
public class QueryEvent {
     private  double eventTime=0;
     private  EventType type;
     private Connection connection;

    QueryEvent(double time, EventType type, Connection connection){
        this.eventTime= time;
        this.type = type;
        this.connection = connection;
    }

    public String getType(){
        return type.getType();
    }
    public double getEventTime(){
        return eventTime;
    }
    public Connection getConnection(){
        return connection;
    }
}

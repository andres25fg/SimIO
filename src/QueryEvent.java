/**
 * Enumeration EventType
 *
 * Enumeration that defines all possible types ov event that can be process in the simulation
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
 * Class QueryEvent
 *
 * This class defines the QueryEvent object. Which is associated to a connection and has the information of the type of event, by using the enum
 */
public class QueryEvent {
     private  double eventTime=0;
     private  EventType type;
     private Connection connection;

    /**
     * Constructor
     * @param time: time of the event
     * @param type: type of event
     * @param connection: associated connection to the event
     */
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

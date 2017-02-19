/**
 * Created by felipe on 17/2/2017.
 */

//enum con los tipos de eventos
 enum EventType {
    CONECTION_IN("connection_in"),
    CONNECTION_OUT("connection_out"),
    TIME_OUT("time_out"),
    EXIT_MODULE("exit_Module");

    private String type;

    EventType(String type){
        this.type=type;
    }

    public String getType(){
        return type;
    }
}
public class QueryEvent {
     private  int eventTime=0;
     private  EventType type;
     private Connection connection;

    QueryEvent(int time, EventType type, Connection connection){
        this.eventTime= time;
        this.type = type;
        this.connection = connection;
    }

    public String getType(){
        return type.getType();
    }
    public int getEventTime(){
        return eventTime;
    }
    public Connection getConnection(){
        return connection;
    }
}

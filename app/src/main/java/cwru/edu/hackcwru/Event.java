package cwru.edu.hackcwru;

public class Event {
    String eventName;
    String eventTime;
    public Event(String eventName, String eventTime){
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventTime() {
        return eventTime;
    }
}

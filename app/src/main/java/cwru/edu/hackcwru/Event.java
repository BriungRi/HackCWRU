package cwru.edu.hackcwru;

public class Event {
    private String eventName;
    private String eventTime;

    private String eventDescription;

    public Event(String eventName, String eventTime, String eventDescription) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String toString(){
        return eventName + " " + eventTime + " " + eventDescription;
    }
}

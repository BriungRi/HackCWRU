package cwru.edu.hackcwru;

public class Event {
    private String eventName;
    private String eventTime;
    private String eventDescription;
    private int index;

    public Event(String eventName, String eventTime, String eventDescription, int index) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.index = index;
    }

    public String getEventTitle() {
        return eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getIndex(){
        return this.index;
    }

    public String toString() {
        return eventName + " " + eventTime + " " + eventDescription;
    }

    @Override
    public boolean equals(Object o) {
        return ((Event) o).getEventTitle().equals(this.getEventTitle());
    }
}

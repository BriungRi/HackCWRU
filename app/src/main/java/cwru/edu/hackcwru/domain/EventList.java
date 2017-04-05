package cwru.edu.hackcwru.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getSavedEvents() {
        List<Event> savedEvents = new ArrayList<>();
        if (events != null) {
            for (Event event : events) {
                if (event.isSaved())
                    savedEvents.add(event);
            }
        }
        return savedEvents;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

package cwru.edu.hackcwru.events;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import cwru.edu.hackcwru.data.Event;

public class EventsPresenter implements EventsContract.Presenter {

    EventsContract.View eventsView;

    public EventsPresenter(EventsContract.View eventsView){
        this.eventsView = eventsView;
        eventsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadEvents();
    }

    @Override
    public void openEventDetails(@NonNull Event event) {

    }

    @Override
    public void saveEvent(@NonNull Event event) {

    }

    @Override
    public void loadEvents() {
        // Creating custom events
        ArrayList<Event> events = new ArrayList<>();
        Event e1 = new Event("Welcome", "Friday 5:00PM - 7:00PM", "Getting unpacked and stuff", 0);
        Event e2 = new Event("Start Hacking", "Friday 7:00PM - ", "Take those computers out and start writing some code!", 1);
        Event e3 = new Event("Ending Ceremony", "Sunday 3:00PM", "Presentation and Awards", 13);
        events.add(e1);
        events.add(e2);
        for (int i = 2; i < 13; i++) {
            events.add(new Event("Cool stuff " + i, "Day time_from - time_to", "Blah blah blah", i));
        }
        events.add(e3);
        processEvents(events);
    }

    private void processEvents(ArrayList<Event> events){
        eventsView.showEvents(events);
    }
}

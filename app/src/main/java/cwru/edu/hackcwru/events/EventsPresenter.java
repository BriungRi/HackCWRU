package cwru.edu.hackcwru.events;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.domain.EventList;
import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.eventdetail.EventDetailContract;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsPresenter implements EventsContract.Presenter, EventDetailContract.Presenter {
    private final String LOG_TAG = "EventsPresenter";

    LocalData localData;

    private EventsContract.View eventsView;

    private EventDetailContract.View eventDetailView;

    private HackCWRUServerCalls hackCWRUServerCalls;

    private List<Event> allEvents;
    private List<Event> savedEvents = new ArrayList<>();

    private boolean showingSavedEvents = false;

    public EventsPresenter(@NonNull LocalData localData, @NonNull HackCWRUServerCalls hackCWRUServerCalls) {
        this.localData = localData;
        this.hackCWRUServerCalls = hackCWRUServerCalls;
    }

    @Override
    public void start() {
        loadEvents();
    }

    @Override
    public void setEventsView(EventsContract.View eventsView) {
        this.eventsView = eventsView;
    }

    @Override
    public void setEventDetailView(EventDetailContract.View eventDetailView) {
        this.eventDetailView = eventDetailView;
    }

    @Override
    public void openEventDetails(@NonNull Event event) {
        eventDetailView.populateEvent(event);
    }

    @Override
    public void saveEvent(@NonNull Event event) {
        String snackBarMessage;
        if (event.isSaved()) {
            savedEvents.add(event);
            snackBarMessage = "Event saved.";
        } else {
            savedEvents.remove(event);
            snackBarMessage = "Event unsaved.";
        }

        eventsView.showOnEventSavedSnackback(snackBarMessage);

        EventList eventListToSave = new EventList();
        eventListToSave.setEvents(this.allEvents);
        localData.saveEventsToLocal(eventListToSave);
    }

    @Override
    public void loadEvents() {
        final EventList localEventList = localData.getEventsFromLocal();
        if (localEventList != null) {
            allEvents = localEventList.getEvents();
            savedEvents = localEventList.getSavedEvents();
            showAllEvents();
        }

        Call<EventList> loadEventsCall = hackCWRUServerCalls.getEventsFromServer();
        loadEventsCall.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(Call<EventList> call, Response<EventList> response) {
                EventList eventsResponse = response.body();
                allEvents = sanityCheckEvents(localEventList, eventsResponse);
                showAllEvents();
                localData.saveEventsToLocal(eventsResponse);
            }

            @Override
            public void onFailure(Call<EventList> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }

    @Override
    public void bookmarkButtonPerformClick() {
        if (showingSavedEvents) {
            eventsView.updateBookmarkButtonBackgroundResource(R.drawable.ic_bookmark_border_white_24dp);
            showAllEvents();
        } else {
            eventsView.updateBookmarkButtonBackgroundResource(R.drawable.ic_bookmark_white_24dp);
            showSavedEvents();
        }
        showingSavedEvents = !showingSavedEvents;
    }

    @Override
    public void showSavedEvents() {
        eventsView.updateBookmarkButtonBackgroundResource(R.drawable.ic_bookmark_white_24dp);
        processEvents(savedEvents);
    }

    @Override
    public void showAllEvents() {
        eventsView.updateBookmarkButtonBackgroundResource(R.drawable.ic_bookmark_border_white_24dp);
        processEvents(allEvents);
    }

    private void processEvents(List<Event> events) {
        if (events.size() == 0) {
            eventsView.showNoEventsText();
        } else {
            eventsView.hideNoEventsText();
        }
        eventsView.showEvents(events);
        eventsView.onRefreshFinish();
    }

    private static List<Event> sanityCheckEvents(EventList localEventList, EventList remoteEventList) {
        List<Event> local = localEventList.getEvents();
        List<Event> remote = remoteEventList.getEvents();

        for (int i = 0; i < remote.size(); i++) {
            if (i < local.size())
                remote.get(i).setSaved(local.get(i).isSaved());
        }
        return remote;
    }
}

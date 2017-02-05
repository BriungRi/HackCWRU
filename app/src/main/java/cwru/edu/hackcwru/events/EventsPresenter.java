package cwru.edu.hackcwru.events;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Event;
import cwru.edu.hackcwru.data.EventList;
import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.eventdetail.EventDetailContract;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsPresenter implements EventsContract.Presenter, EventDetailContract.Presenter {
    private final String LOG_TAG = "EventsPresenter";

    SharedPreferences sharedPreferences;

    EventsContract.View eventsView;

    EventDetailContract.View eventDetailView;

    HackCWRUServerCalls hackCWRUServerCalls;

    List<Event> allEvents;
    List<Event> savedEvents;

    boolean showingSavedEvents = false;

    @Inject
    public EventsPresenter(@NonNull EventsContract.View eventsView,
                           @NonNull EventDetailContract.View eventDetailView,
                           SharedPreferences sharedPreferences) {
        this.eventsView = eventsView;
        this.eventDetailView = eventDetailView;
        this.sharedPreferences = sharedPreferences;
        eventsView.setPresenter(this);
        eventDetailView.setPresenter(this);

        initializeRetrofit();

        savedEvents = new ArrayList<>();
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HackCWRUServerCalls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        hackCWRUServerCalls = retrofit.create(HackCWRUServerCalls.class);
    }

    @Override
    public void start() {
        loadEvents();
    }

    @Override
    public void openEventDetails(@NonNull Event event) {
        eventDetailView.populateEvent(event);
    }

    @Override
    public void saveEvent(@NonNull Event event) {
        String snackBarMessage;
        if (event.isSaved()) {
            event.setSaved(false);
            savedEvents.remove(event);
            snackBarMessage = "Event unsaved.";
        } else {
            event.setSaved(true);
            savedEvents.add(event);
            snackBarMessage = "Event saved.";
        }

        eventsView.showOnEventSavedSnackback(snackBarMessage);
    }

    @Override
    public void loadEvents() {
        // TODO: Check server against local
        EventList localEventList = LocalData.getEventsFromLocal(this.sharedPreferences);
        if(localEventList != null) {
            allEvents = localEventList.getEvents();
            showAllEvents();
        }

        Call<EventList> loadEventsCall = hackCWRUServerCalls.getEventsFromServer();
        loadEventsCall.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(Call<EventList> call, Response<EventList> response) {
                // TODO: Perform data check with server and current
                EventList eventsResponse = response.body();
                Log.d(LOG_TAG, eventsResponse.toString());
                allEvents = eventsResponse.getEvents();
                showAllEvents();
                LocalData.saveEventsToLocal(EventsPresenter.this.sharedPreferences, eventsResponse);
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
}

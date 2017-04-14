package cwru.edu.hackcwru.events;

import android.support.annotation.NonNull;

import java.util.List;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.eventdetail.EventDetailContract;

public interface EventsContract {

    interface View extends BaseView<Presenter> {
        void showEvents(List<Event> events);

        void onRefreshFinish();

        void showNoEventsText();

        void hideNoEventsText();

        void showOnEventSavedSnackback(String message);

        void updateBookmarkButtonBackgroundResource(int resourceId);
    }

    interface Presenter extends BasePresenter {
        void setEventsView(EventsContract.View eventsView);

        void setEventDetailView(EventDetailContract.View eventDetailView);

        void openEventDetails(@NonNull Event event);

        void saveEvent(@NonNull Event event);

        void loadEvents();

        void bookmarkButtonPerformClick();

        void showSavedEvents();

        void showAllEvents();

        void saveMaps();
    }

}

package cwru.edu.hackcwru.events;

import android.support.annotation.NonNull;

import java.util.List;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;
import cwru.edu.hackcwru.data.Event;

public interface EventsContract {

    interface View extends BaseView<Presenter> {
        void showEvents(List<Event> events);

        void onRefreshFinish();

        void showNoEventsText();

        void hideNoEventsText();

        void showOnEventSavedSnackback(String message);

        void updateBookmarkButtonBackgroundResource(int reosurceId);
    }

    interface Presenter extends BasePresenter {
        void openEventDetails(@NonNull Event event);

        void saveEvent(@NonNull Event event);

        void loadEvents();

        void bookmarkButtonPerformClick();

        void showSavedEvents();

        void showAllEvents();
    }

}

package cwru.edu.hackcwru.eventdetail;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;
import cwru.edu.hackcwru.domain.Event;

public interface EventDetailContract {
    interface View extends BaseView<Presenter> {
        void populateEvent(Event event);

        void populateMap(String imageUrl);
    }

    interface Presenter extends BasePresenter {
    }
}

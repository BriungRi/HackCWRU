package cwru.edu.hackcwru.eventdetail;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;
import cwru.edu.hackcwru.data.Event;

public interface EventDetailContract {
    interface View extends BaseView<Presenter> {
        void populateEvent(Event event);
    }

    interface Presenter extends BasePresenter {
    }
}

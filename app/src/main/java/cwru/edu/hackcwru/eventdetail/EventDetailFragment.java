package cwru.edu.hackcwru.eventdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.events.EventsActivity;
import cwru.edu.hackcwru.events.EventsPresenter;
import cwru.edu.hackcwru.utils.TimeUtils;

public class EventDetailFragment extends Fragment implements EventDetailContract.View {
    private final String LOG_TAG = "EventDetailFragment";

    @Inject
    EventsPresenter presenter;

    @BindView(R.id.event_detail_title)
    TextView eventDetailTitle;
    @BindView(R.id.event_detail_time)
    TextView eventDetailTime;
    @BindView(R.id.event_detail_description)
    TextView eventDetailDescription;

    private Unbinder unbinder;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    public EventDetailFragment() {
        // Empty Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HackCWRUApplication) getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_detail_fragment, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        presenter.setEventDetailView(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.setEventDetailView(null);
    }

    @Override
    public void populateEvent(Event event) {
        if (event != null) {
            eventDetailTitle.setText(event.getName());
            eventDetailTime.setText(event.getStartDateTime() + " - " + event.getEndDateTime());
            eventDetailDescription.setText(event.getDescription());
        } else
            throw new NullPointerException();
        EventsActivity activity = (EventsActivity) getActivity();
        if (activity != null) {
            activity.showEventDetail();
        } else
            throw new NullPointerException();
    }
}
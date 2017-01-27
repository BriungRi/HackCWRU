package cwru.edu.hackcwru.eventdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Event;
import cwru.edu.hackcwru.events.EventsActivity;

public class EventDetailFragment extends Fragment implements EventDetailContract.View {
    private final String LOG_TAG = "EventDetailFragment";

    private EventDetailContract.Presenter presenter;

    @BindView(R.id.event_detail_title)
    TextView eventDetailTitle;
    @BindView(R.id.event_detail_time)
    TextView eventDetailTime;
    @BindView(R.id.event_detail_description)
    TextView eventDetailDescription;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    public EventDetailFragment() {
        // Empty Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_detail_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void setPresenter(EventDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void populateEvent(Event event) {
        if (event != null) {
            eventDetailTitle.setText(event.getName());
            // TODO: Prettify the times
            eventDetailTime.setText(event.getStartDateTime() + " - " + event.getEndDateTime());
            eventDetailDescription.setText(event.getDescription());
        } else
            throw new NullPointerException();
        EventsActivity activity = (EventsActivity) getActivity();
        if(activity != null){
            activity.showEventDetail();
        }
        else
            throw new NullPointerException();
    }
}
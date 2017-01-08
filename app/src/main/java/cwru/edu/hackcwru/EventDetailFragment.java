package cwru.edu.hackcwru;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.data.Event;

public class EventDetailFragment extends Fragment{
    private final String LOG_TAG = "EventDetailFragment";

    private Event event;

    @BindView(R.id.event_detail_title)
    TextView eventDetailTitle;
    @BindView(R.id.event_detail_time)
    TextView eventDetailTime;
    @BindView(R.id.event_detail_description)
    TextView eventDetailDescription;

    public EventDetailFragment(Event event){
        this.event = event;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.event_detail_fragment, container, false);
        ButterKnife.bind(this, rootView);

        eventDetailTitle.setText(event.getEventTitle());
        eventDetailTime.setText(event.getEventTime());
        eventDetailDescription.setText(event.getEventDescription());
        return rootView;
    }
}

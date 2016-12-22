package cwru.edu.hackcwru;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailFragment extends Fragment{
    private final String LOG_TAG = "EventDetailFragment";

    private Event event;

    @BindView(R.id.event_detail_title)
    TextView eventDetailTitle;
    @BindView(R.id.event_detail_time)
    TextView eventDetailTime;
    @BindView(R.id.event_detail_description)
    TextView eventDetailDescription;

//    @BindView(R.id.main_toolbar)
//    Toolbar mainToolbar;

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


//        this.mainToolbar.setNavigationIcon(R.drawable.ic_action_back);


        eventDetailTitle.setText(event.getEventName());
        eventDetailTime.setText(event.getEventTime());
        eventDetailDescription.setText(event.getEventDescription());
        return rootView;
    }
}

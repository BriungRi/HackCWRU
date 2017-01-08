package cwru.edu.hackcwru.eventdetail;

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
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Event;

public class EventDetailActivity extends AppCompatActivity{
    private final String LOG_TAG = "EventDetailActivity";

    private Event event;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.event_detail_title)
    TextView eventDetailTitle;
    @BindView(R.id.event_detail_time)
    TextView eventDetailTime;
    @BindView(R.id.event_detail_description)
    TextView eventDetailDescription;

    public EventDetailActivity(Event event){
        this.event = event;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

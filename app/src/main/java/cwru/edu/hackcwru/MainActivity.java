package cwru.edu.hackcwru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.event_list)
    RecyclerView eventList;
    private EventListAdapter eventListAdapter;
    private RecyclerView.LayoutManager eventLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Event e1 = new Event("Welcome", "Friday 5:00PM - 7:00PM");
        Event e2 = new Event("Start Hacking", "Friday 7:00PM - ");
        Event e3 = new Event("Ending Ceremony", "Sunday 3:00PM");
        Event [] events = {e1, e2, e3};

        eventList.setHasFixedSize(true);

        eventLayoutManager = new LinearLayoutManager(this);
        eventList.setLayoutManager(eventLayoutManager);

        eventListAdapter = new EventListAdapter(events);
        eventList.setAdapter(eventListAdapter);
    }


}

class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    private Event[] events;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView eventName;
        public TextView eventTime;

        public ViewHolder(View v) {
            super(v);
            eventName = (TextView) v.findViewById(R.id.event_name);
            eventTime = (TextView) v.findViewById(R.id.event_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventListAdapter(Event[] events) {
        this.events = events;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.eventName.setText(events[position].getEventName());
        holder.eventTime.setText(events[position].getEventTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events.length;
    }
}


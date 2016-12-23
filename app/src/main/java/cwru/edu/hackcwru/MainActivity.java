package cwru.edu.hackcwru;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.utils.FragmentUtils;
import cwru.edu.hackcwru.utils.Log;
import cwru.edu.hackcwru.utils.UIUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static String LOG_TAG = "Main Activity";
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private boolean isToolBarNavigationListenerRegistered = false;

    @BindView(R.id.event_list)
    RecyclerView eventList;
    private EventListAdapter eventListAdapter;
    private RecyclerView.LayoutManager eventLayoutManager;

    public ArrayList<String> savedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadPreferences();

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup menu navigation with toolbar
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.app_name, R.string.app_name) {

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                UIUtils.toast(MainActivity.this, item.getTitle().toString());
                return super.onOptionsItemSelected(item);
            }
        };
        this.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // Default to schedule
        navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView.setNavigationItemSelectedListener(this);

        // Creating custom events
        Event e1 = new Event("Welcome", "Friday 5:00PM - 7:00PM", "Getting unpacked and stuff");
        Event e2 = new Event("Start Hacking", "Friday 7:00PM - ", "Take those computers out and start writing some code!");
        Event e3 = new Event("Ending Ceremony", "Sunday 3:00PM", "Presentation and Awards");
        Event[] events = new Event[15];
        events[0] = e1;
        events[1] = e2;
        for (int i = 2; i < events.length - 1; i++) {
            events[i] = new Event("Cool stuff " + i, "Day time_from - time_to", "Blah blah blah");
        }
        events[14] = e3;

        // End of custom events

        eventList.setHasFixedSize(true);
        eventLayoutManager = new LinearLayoutManager(this);
        eventList.setLayoutManager(eventLayoutManager);
        eventListAdapter = new EventListAdapter(this, events);
        eventList.setAdapter(eventListAdapter);
        eventList.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    @Override
    protected void onStop() {
        savePreferences();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_overflow:

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentUtils.closeEventDetailFragment(this);
        FragmentUtils.closeCountdownFragment(this);
        mainToolbar.setTitle(getString(R.string.schedule_title));
        drawerLayout.closeDrawers();
        showUpButton(false);
    }

    public void showUpButton(boolean show) {
        if (show) {
            // Replace hamburger with back arrow
            drawerToggle.setDrawerIndicatorEnabled(false);
            // Create onClickListener() for back button
            if (!isToolBarNavigationListenerRegistered) {
                drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                isToolBarNavigationListenerRegistered = true;
            }
        } else {
            // Replace back arrow with hamburger
            drawerToggle.setDrawerIndicatorEnabled(true);
            // Revert to original hamburger onClickListener()
            drawerToggle.setToolbarNavigationClickListener(null);
            isToolBarNavigationListenerRegistered = false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);

        // Closing drawer on item click
        drawerLayout.closeDrawers();

        if (item.getItemId() == R.id.item_maps || item.getItemId() == R.id.item_announcements) {
            UIUtils.toast(this, "Currently unavailable.");
            item.setChecked(false);
            return false;
        }

        mainToolbar.setTitle(item.getTitle());

        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {
            case R.id.item_schedule:
                onBackPressed();
                return true;
            case R.id.item_maps:
                return false;
            case R.id.item_announcements:
                return false;
            case R.id.item_countdown:
                FragmentUtils.showCountdownFragment(this);
                return true;
        }
        return false;
    }

    private void loadPreferences(){
        Log.d(LOG_TAG, "loadPreferences()");
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.session_preferences), Context.MODE_PRIVATE);
        Set<String> set = settings.getStringSet(getString(R.string.saved_events_preference), new HashSet<String>());
        savedEvents = new ArrayList<>();
        savedEvents.addAll(set);
        Log.d(LOG_TAG, savedEvents.toString());
    }

    private void savePreferences(){
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.session_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Set<String> set = new HashSet<>();
        set.addAll(savedEvents);
        Log.d(LOG_TAG, set.toString());
        editor.putStringSet(getString(R.string.saved_events_preference), set);
        editor.commit();
    }


    public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
        private Event[] events;
        private MainActivity activity;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            @BindView(R.id.list_event_name)
            TextView eventName;
            @BindView(R.id.list_event_description)
            TextView eventDescription;
            @BindView(R.id.list_event_time)
            TextView eventTime;
            @BindView(R.id.save_button)
            ImageView saveButton;
            private boolean saved;
            @BindView(R.id.list_item_view)
            View item;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public EventListAdapter(MainActivity activity, Event[] events) {
            this.events = events;
            this.activity = activity;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Event event = events[position];
            holder.eventName.setText(event.getEventTitle());
            holder.eventTime.setText(event.getEventTime());
            holder.eventDescription.setText(event.getEventDescription());
            holder.saved = savedEvents.contains(event.getEventTitle());
            if(holder.saved)
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
            else
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentUtils.showEventDetailFragment(activity, event);
                    activity.showUpButton(true);
                }
            });
            holder.saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.saved) {
                        savedEvents.remove(event.getEventTitle());
                        view.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                    }
                    else{
                        savedEvents.add(event.getEventTitle());
                        view.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
                    }
                    holder.saved = !holder.saved;
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return events.length;
        }
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

}
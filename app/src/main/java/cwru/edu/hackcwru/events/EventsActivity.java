package cwru.edu.hackcwru.events;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import cwru.edu.hackcwru.data.Event;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.utils.ActivityUtils;
import cwru.edu.hackcwru.utils.FragmentUtils;
import cwru.edu.hackcwru.utils.Log;
import cwru.edu.hackcwru.utils.UIUtils;

public class EventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static String LOG_TAG = "Main Activity";

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    private EventsPresenter eventsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity);
        ButterKnife.bind(this);

        // Setup Toolbar
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Setup menu navigation with toolbar
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                UIUtils.toast(EventsActivity.this, item.getTitle().toString());
                return super.onOptionsItemSelected(item);
            }
        };

        this.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // Default to schedule
        navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView.setNavigationItemSelectedListener(this);

        EventsFragment eventsFragment = (EventsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(eventsFragment == null) {
            eventsFragment = EventsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), eventsFragment, R.id.content_frame);
        }

        eventsPresenter = new EventsPresenter(eventsFragment);
    }

    @Override
    protected void onStop() {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);

        if (item.getItemId() == R.id.item_maps || item.getItemId() == R.id.item_announcements) {
            UIUtils.toast(this, "Currently unavailable.");
            item.setChecked(false);
            return false;
        }

        mainToolbar.setTitle(item.getTitle());

        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {
            case R.id.item_schedule:
//                return false;
            case R.id.item_maps:
//                return false;
            case R.id.item_announcements:
//                return false;
            case R.id.item_countdown:
//                return false;
        }

        drawerLayout.closeDrawers();
        return false;
    }

    private void loadPreferences() {
//        Log.d(LOG_TAG, "loadPreferences()");
//        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.session_preferences), Context.MODE_PRIVATE);
//        Set<String> set = settings.getStringSet(getString(R.string.saved_events_preference), new HashSet<String>());
//        savedEvents = new ArrayList<>();
//        savedEvents.addAll(set);
//        Log.d(LOG_TAG, savedEvents.toString());
    }

    private void savePreferences() {
//        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.session_preferences), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = settings.edit();
//        Set<String> set = new HashSet<>();
//        set.addAll(savedEvents);
//        Log.d(LOG_TAG, set.toString());
//        editor.putStringSet(getString(R.string.saved_events_preference), set);
//        editor.commit();
    }
}
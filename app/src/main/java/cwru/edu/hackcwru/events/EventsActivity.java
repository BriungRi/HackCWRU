package cwru.edu.hackcwru.events;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.eventdetail.EventDetailFragment;
import cwru.edu.hackcwru.utils.ActivityUtils;
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

        // Get device width/height. Set right drawer width to device width.
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.findViewById(R.id.right_drawer)
                .setLayoutParams(new DrawerLayout.LayoutParams(displaymetrics.widthPixels, displaymetrics.heightPixels, GravityCompat.END));

        // Setup Toolbar
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // Setup menu navigation with toolbar (This constructor will use the onOptionsItemSelected() method for onClicks()
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        this.drawerLayout.addDrawerListener(drawerToggle);

        // Prevent main content from dimming while drawer is open
        this.drawerLayout.setScrimColor(Color.TRANSPARENT);
        this.drawerToggle.syncState();

        // Default selected item to schedule
        this.navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView.setNavigationItemSelectedListener(this);

        // Attach Events and EventDetail Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        EventsFragment eventsFragment = (EventsFragment) fragmentManager.findFragmentById(R.id.content_frame);
        if (eventsFragment == null) {
            eventsFragment = EventsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, eventsFragment, R.id.content_frame);
        }

        EventDetailFragment eventDetailFragment = (EventDetailFragment) fragmentManager.findFragmentById(R.id.right_drawer);
        if (eventDetailFragment == null) {
            eventDetailFragment = EventDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, eventDetailFragment, R.id.right_drawer);
        }

        eventsPresenter = new EventsPresenter(eventsFragment, eventDetailFragment);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(GravityCompat.END))
                    onBackPressed();
                else
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;
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
            case R.id.item_maps:
            case R.id.item_announcements:
            case R.id.item_countdown:
        }

        drawerLayout.closeDrawers();
        return false;
    }

    public void showEventDetail() {
        drawerLayout.openDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START) || drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
        }
        else
            super.onBackPressed();
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
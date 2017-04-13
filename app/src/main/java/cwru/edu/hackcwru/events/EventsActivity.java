package cwru.edu.hackcwru.events;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import cwru.edu.hackcwru.utils.FragmentUtils;
import cwru.edu.hackcwru.utils.UIUtils;

public class EventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String LOG_TAG = "Main Activity";

    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

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

        setupToolbar();

        // Setup menu navigation with toolbar (This constructor will use the onOptionsItemSelected() method for onClicks()
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {

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
        drawerToggle.syncState();

        // Prevent swiping from right
        this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
        this.drawerLayout.addDrawerListener(drawerToggle);

        // Prevent main content from dimming while drawer is open
        this.drawerLayout.setScrimColor(Color.TRANSPARENT);

        // Default selected item to schedule
        this.navigationView.getMenu().getItem(0).setChecked(true);
        this.navigationView.setNavigationItemSelectedListener(this);

        if (FragmentUtils.fragmentsAreAllClosed())
            attachFragments();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.END))
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
        // Don't do anything if this is the current item
        if (item.isChecked())
            return true;

        mainToolbar.setTitle(item.getTitle());

        FragmentUtils.closeAllOverlayElements(EventsActivity.this);
        switch (item.getItemId()) {
            case R.id.item_schedule:
                attachFragments();
                break;
            case R.id.item_maps:
                FragmentUtils.showMapsFragment(EventsActivity.this);
                break;
            case R.id.item_announcements:
                FragmentUtils.showAnnouncementsFragment(EventsActivity.this);
                break;
            case R.id.item_countdown:
                FragmentUtils.showCountdownFragment(EventsActivity.this);
                break;
            case R.id.item_mentor:
                FragmentUtils.showMentorFragment(EventsActivity.this);
                break;
            case R.id.item_credits:
                FragmentUtils.showCreditsFragment(EventsActivity.this);
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START) || drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
        } else
            super.onBackPressed();
    }

    public void showEventDetail() {
        drawerLayout.openDrawer(GravityCompat.END);
    }

    private void setupToolbar() {
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void attachFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment eventsFragment = fragmentManager.findFragmentById(R.id.content_frame);
        if (eventsFragment == null || !(eventsFragment instanceof EventsFragment)) {
            eventsFragment = EventsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, eventsFragment, R.id.content_frame);
        }

        EventDetailFragment eventDetailFragment = (EventDetailFragment) fragmentManager.findFragmentById(R.id.right_drawer);
        if (eventDetailFragment == null) {
            eventDetailFragment = EventDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, eventDetailFragment, R.id.right_drawer);
        }
    }
}
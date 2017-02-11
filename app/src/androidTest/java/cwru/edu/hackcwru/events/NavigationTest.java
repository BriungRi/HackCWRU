package cwru.edu.hackcwru.events;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cwru.edu.hackcwru.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static cwru.edu.hackcwru.TestUtils.getToolbarNavigationContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests for the {@link DrawerLayout} layout component in {@link cwru.edu.hackcwru.events.EventsActivity} which manages
 * navigation within the app.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     * <p>
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<EventsActivity> mActivityTestRule =
            new ActivityTestRule<>(EventsActivity.class);

//    @Test
//    public void clickOnAndroidHomeIcon_OpensNavigation() {
//        // Check that left drawer is closed at startup
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
//
//        // Open Drawer
//        onView(withContentDescription(getToolbarNavigationContentDescription(
//                mActivityTestRule.getActivity(), R.id.toolbar))).perform(click());
//
//        // Check if drawer is open
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isOpen(Gravity.LEFT))); // Left drawer is open open.
//    }
}
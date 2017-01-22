package cwru.edu.hackcwru.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.countdown.CountdownFragment;
import cwru.edu.hackcwru.events.EventsActivity;
import cwru.edu.hackcwru.events.EventsFragment;

public class FragmentUtils {

    private static void showOverlayElement(FragmentActivity activity, Fragment fragment){
        showOverlayElement(activity, fragment, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void showOverlayElement(FragmentActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    private static void closeOverlayElement(FragmentActivity activity, Fragment fragment){
        closeOverlayElement(activity, fragment, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void closeOverlayElement(FragmentActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        if (fragment != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(enterAnimation, exitAnimation);
            transaction.remove(fragment);
            transaction.commit();
        }
    }

    public static void closeAllOverlayElements(EventsActivity activity){
        closeCountdownFragment(activity);
    }

    private static CountdownFragment countdownFragment;
    public static void showCountdownFragment(EventsActivity activity) {
        if (countdownFragment == null) {
            countdownFragment = CountdownFragment.newInstance();
            showOverlayElement(activity, countdownFragment);
        }
    }

    public static void closeCountdownFragment(EventsActivity activity) {
        closeOverlayElement(activity, countdownFragment);
        countdownFragment = null;
    }

    private static EventsFragment eventsFragment;
    public static void showEventsFragment(EventsActivity activity) {
        if (eventsFragment == null) {
            eventsFragment = EventsFragment.newInstance();
            showOverlayElement(activity, eventsFragment);
        }
    }

    public static void closeEventsFragment(EventsActivity activity) {
        closeOverlayElement(activity, eventsFragment);
        eventsFragment = null;
    }
}

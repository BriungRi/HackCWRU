package cwru.edu.hackcwru.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import cwru.edu.hackcwru.CountdownFragment;
import cwru.edu.hackcwru.Event;
import cwru.edu.hackcwru.EventDetailFragment;
import cwru.edu.hackcwru.MainActivity;
import cwru.edu.hackcwru.R;

public class FragmentUtils {
    private static void showOverlayElement(MainActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.fragment_placeholder, fragment);
        transaction.commit();
    }

    private static void closeOverlayElement(MainActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        if (fragment != null) {
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(enterAnimation, exitAnimation);
            transaction.remove(fragment);
            transaction.commit();
        }
    }

    private static EventDetailFragment eventDetailFragment;

    public static void showEventDetailFragment(MainActivity activity, Event event) {
        eventDetailFragment = new EventDetailFragment(event);
        showOverlayElement(activity, eventDetailFragment, R.animator.enter_from_right, R.animator.exit_to_left);
    }

    public static void closeEventDetailFragment(MainActivity activity) {
        closeOverlayElement(activity, eventDetailFragment, R.animator.enter_from_left, R.animator.exit_to_right);
        eventDetailFragment = null;
    }

    private static CountdownFragment countdownFragment;

    public static void showCountdownFragment(MainActivity activity) {
        if (countdownFragment == null) {
            countdownFragment = new CountdownFragment();
            showOverlayElement(activity, countdownFragment, android.R.animator.fade_in, android.R.animator.fade_out);
        }
    }

    public static void closeCountdownFragment(MainActivity activity) {
        closeOverlayElement(activity, countdownFragment, android.R.animator.fade_in, android.R.animator.fade_out);
        countdownFragment = null;
    }
}

package cwru.edu.hackcwru.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import cwru.edu.hackcwru.Event;
import cwru.edu.hackcwru.EventDetailFragment;
import cwru.edu.hackcwru.MainActivity;
import cwru.edu.hackcwru.R;

public class FragmentUtils {
    private static void showOverlayElement(MainActivity activity, Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.replace(R.id.fragment_placeholder, fragment);
        transaction.commit();
    }

    private static void closeOverlayElement(MainActivity activity, Fragment fragment) {
        if (fragment != null) {
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            transaction.remove(fragment);
            transaction.commit();
        }
    }

    private static EventDetailFragment eventDetailFragment;

    public static void showEventDetailFragment(MainActivity activity, Event event) {
        eventDetailFragment = new EventDetailFragment(event);
        showOverlayElement(activity, eventDetailFragment);
    }

    public static void closeEventDetailFragment(MainActivity activity) {
        closeOverlayElement(activity, eventDetailFragment);
    }
}

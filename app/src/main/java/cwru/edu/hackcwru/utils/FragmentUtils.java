package cwru.edu.hackcwru.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Event;
import cwru.edu.hackcwru.eventdetail.EventDetailFragment;

public class FragmentUtils {
    private static void showOverlayElement(FragmentActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
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

//    private static EventDetailFragment eventDetailFragment;

//    public static void showEventDetailFragment(FragmentActivity activity, Event event) {
//        eventDetailFragment = new EventDetailFragment(event);
//        showOverlayElement(activity, eventDetailFragment, R.anim.enter_from_right, R.anim.exit_to_left);
//    }
//
//    public static void closeEventDetailFragment(FragmentActivity activity) {
//        closeOverlayElement(activity, eventDetailFragment, R.anim.enter_from_left, R.anim.exit_to_right);
//        eventDetailFragment = null;
//    }
//
//    private static CountdownActivity countdownFragment;
//
//    public static void showCountdownFragment(EventsActivity activity) {
//        if (countdownFragment == null) {
//            countdownFragment = new CountdownActivity();
//            showOverlayElement(activity, countdownFragment, android.R.animator.fade_in, android.R.animator.fade_out);
//        }
//    }
//
//    public static void closeCountdownFragment(EventsActivity activity) {
//        closeOverlayElement(activity, countdownFragment, android.R.animator.fade_in, android.R.animator.fade_out);
//        countdownFragment = null;
//    }
}

package cwru.edu.hackcwru.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.announcements.AnnouncementsFragment;
import cwru.edu.hackcwru.countdown.CountdownFragment;
import cwru.edu.hackcwru.credits.CreditsFragment;
import cwru.edu.hackcwru.events.EventsActivity;
import cwru.edu.hackcwru.maps.MapsFragment;
import cwru.edu.hackcwru.mentor.MentorFragment;

public class FragmentUtils {

    private static void showOverlayElement(FragmentActivity activity, Fragment fragment) {
        showOverlayElement(activity, fragment, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void showOverlayElement(FragmentActivity activity, Fragment fragment, int enterAnimation, int exitAnimation) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    private static void closeOverlayElement(FragmentActivity activity, Fragment fragment) {
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

    public static void closeAllOverlayElements(EventsActivity activity) {
        closeAnnouncementsFragment(activity);
        closeMapsFragment(activity);
        closeCountdownFragment(activity);
        closeMentorFragment(activity);
        closeCreditsFragment(activity);
    }

    private static CountdownFragment countdownFragment;

    public static void showCountdownFragment(EventsActivity activity) {
        if (countdownFragment == null) {
            countdownFragment = CountdownFragment.newInstance();
            showOverlayElement(activity, countdownFragment);
        }
    }

    private static void closeCountdownFragment(EventsActivity activity) {
        closeOverlayElement(activity, countdownFragment);
        countdownFragment = null;
    }

    private static MapsFragment mapsFragment;

    public static void showMapsFragment(EventsActivity activity) {
        if (mapsFragment == null) {
            mapsFragment = MapsFragment.newInstance();
            showOverlayElement(activity, mapsFragment);
        }
    }

    private static void closeMapsFragment(EventsActivity activity) {
        closeOverlayElement(activity, mapsFragment);
        mapsFragment = null;
    }

    private static AnnouncementsFragment announcementsFragment;

    public static void showAnnouncementsFragment(EventsActivity activity) {
        if (announcementsFragment == null) {
            announcementsFragment = AnnouncementsFragment.newInstance();
            showOverlayElement(activity, announcementsFragment);
        }
    }

    private static void closeAnnouncementsFragment(EventsActivity activity) {
        closeOverlayElement(activity, announcementsFragment);
        announcementsFragment = null;
    }

    private static MentorFragment mentorFragment;

    public static void showMentorFragment(EventsActivity activity) {
        if (mentorFragment == null) {
            mentorFragment = MentorFragment.newInstance();
            showOverlayElement(activity, mentorFragment);
        }
    }

    private static void closeMentorFragment(EventsActivity activity) {
        closeOverlayElement(activity, mentorFragment);
        mentorFragment = null;
    }

    private static CreditsFragment creditsFragment;

    public static void showCreditsFragment(EventsActivity activity) {
        if (creditsFragment == null) {
            creditsFragment = CreditsFragment.newInstance();
            showOverlayElement(activity, creditsFragment);
        }
    }

    private static void closeCreditsFragment(EventsActivity activity) {
        closeOverlayElement(activity, creditsFragment);
        creditsFragment = null;
    }

    public static boolean fragmentsAreAllClosed() {
        return announcementsFragment == null && mapsFragment == null && countdownFragment == null && mentorFragment == null && creditsFragment == null;
    }
}

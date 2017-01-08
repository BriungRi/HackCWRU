package cwru.edu.hackcwru.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import cwru.edu.hackcwru.events.EventsActivity;

public class UIUtils {
    private static Toast toast;

    public static void toast(Context context, String message) {
        cancelToast();
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void cancelToast() {
        if (toast != null)
            toast.cancel();
    }

    public static boolean canRecordAudio(EventsActivity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isConnected(EventsActivity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(!isConnected)
            UIUtils.toast(activity, "No Internet Connection.");
        return isConnected;

    }
}

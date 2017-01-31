package cwru.edu.hackcwru.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import cwru.edu.hackcwru.events.EventsActivity;

public class UIUtils {
    private static final String LOG_TAG = "UIUtils";
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

    private static Snackbar snackbar;
    public static void showSnackBar(View view, String message){
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        snackbar.dismiss();
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

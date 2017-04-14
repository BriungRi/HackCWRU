package cwru.edu.hackcwru.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;

import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseService extends FirebaseInstanceIdService {
    private final String LOG_TAG = "FirebaseService";

    @Inject
    HackCWRUServerCalls hackCWRUServerCalls;
    @Inject
    LocalData localData;

    @Override
    public void onCreate() {
        super.onCreate();
        ((HackCWRUApplication) getApplication()).getNetComponent().inject(this);
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG_TAG, "Refreshed token: " + refreshedToken);

        sendDeviceTokenToServer(refreshedToken);
        localData.saveDeviceToken(refreshedToken);
    }

    private void sendDeviceTokenToServer(String deviceToken) {
        Call<DeviceTokenResponse> registerDevice = hackCWRUServerCalls.registerDevice(getString(R.string.HACKCWRU_API_KEY),
                getString(R.string.operating_system),
                deviceToken);
        registerDevice.enqueue(new Callback<DeviceTokenResponse>() {
            @Override
            public void onResponse(Call<DeviceTokenResponse> call, Response<DeviceTokenResponse> response) {
                Log.d(LOG_TAG, "Device Registration Success");
                DeviceTokenResponse deviceTokenResponse = response.body();
                if (deviceTokenResponse != null) {
                    Log.d(LOG_TAG, deviceTokenResponse.toString());
                }
            }

            @Override
            public void onFailure(Call<DeviceTokenResponse> call, Throwable t) {
                Log.d(LOG_TAG, "Error Registering Device");
            }
        });
    }
}

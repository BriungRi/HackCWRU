package cwru.edu.hackcwru.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseService extends FirebaseInstanceIdService {
    private final String LOG_TAG = "FirebaseService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG_TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String deviceToken) {
        HackCWRUServerCalls hackCWRUServerCalls = getHackCWRUServerCalls();

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

    private HackCWRUServerCalls getHackCWRUServerCalls() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HackCWRUServerCalls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(HackCWRUServerCalls.class);
    }
}

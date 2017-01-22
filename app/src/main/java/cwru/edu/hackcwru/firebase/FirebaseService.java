package cwru.edu.hackcwru.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseService extends FirebaseInstanceIdService {
    private final String LOG_TAG = "Firebase Service";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG_TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HackCWRUServerCalls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HackCWRUServerCalls hackCWRUServerCalls = retrofit.create(HackCWRUServerCalls.class);

        Call<DeviceTokenResponse> registerDevice = hackCWRUServerCalls.registerDevice(token);
        registerDevice.enqueue(new Callback<DeviceTokenResponse>() {
            @Override
            public void onResponse(Call<DeviceTokenResponse> call, Response<DeviceTokenResponse> response) {
                Log.d(LOG_TAG, response.toString());
            }

            @Override
            public void onFailure(Call<DeviceTokenResponse> call, Throwable t) {
                Log.d(LOG_TAG, "Error Registering Device");
            }
        });
    }
}

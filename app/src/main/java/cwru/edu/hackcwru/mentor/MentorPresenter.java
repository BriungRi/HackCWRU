package cwru.edu.hackcwru.mentor;

import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.domain.Mentee;
import cwru.edu.hackcwru.domain.MentorRequestResponse;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MentorPresenter implements MentorContract.Presenter {
    private final String LOG_TAG = "Mentor Presenter";

    private HackCWRUServerCalls hackCWRUServerCalls;
    private LocalData localData;

    private MentorContract.View mentorView;

    public MentorPresenter(HackCWRUServerCalls hackCWRUServerCalls, LocalData localData) {
        this.hackCWRUServerCalls = hackCWRUServerCalls;
        this.localData = localData;
    }

    @Override
    public void start() {

    }

    @Override
    public void setMentorView(MentorContract.View view) {
        this.mentorView = view;
    }

    @Override
    public void requestMentor(String apikey, String name, String topics, String location) {
        String deviceToken = localData.getDeviceToken();
        Call<MentorRequestResponse> mentorRequestResponseCall =
                hackCWRUServerCalls.sendMentorRequest(apikey, location, topics, name, deviceToken);

        mentorRequestResponseCall.enqueue(new Callback<MentorRequestResponse>() {
            @Override
            public void onResponse(Call<MentorRequestResponse> call, Response<MentorRequestResponse> response) {
                MentorRequestResponse mentorRequestResponse = response.body();
                Log.d(LOG_TAG, mentorRequestResponse.toString());
            }

            @Override
            public void onFailure(Call<MentorRequestResponse> call, Throwable t) {
                Log.d(LOG_TAG, "Mentore Request Response failed");
            }
        });

        mentorView.clearFields();

    }
}

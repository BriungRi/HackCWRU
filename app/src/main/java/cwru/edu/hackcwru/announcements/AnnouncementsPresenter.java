package cwru.edu.hackcwru.announcements;

import cwru.edu.hackcwru.domain.AnnouncementList;
import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsPresenter implements AnnouncementsContract.Presenter {
    private final String LOG_TAG = "AnnouncementsPresenter";

    private LocalData localData;
    private HackCWRUServerCalls hackCWRUServerCalls;

    private AnnouncementsContract.View announcementsView;

    public AnnouncementsPresenter(LocalData localData, HackCWRUServerCalls hackCWRUServerCalls) {
        this.localData = localData;
        this.hackCWRUServerCalls = hackCWRUServerCalls;
    }

    @Override
    public void start() {
        loadAnnouncementsFromLocal();
        loadAnnouncemntsFromRemote();
    }

    @Override
    public void setAnnouncementsView(AnnouncementsContract.View announcementsView) {
        this.announcementsView = announcementsView;
    }

    @Override
    public void loadAnnouncements() {
        this.loadAnnouncemntsFromRemote();
    }

    @Override
    public void loadAnnouncementsFromLocal() {
        AnnouncementList announcementList = this.localData.getAnnouncementsFromLocal();
        if (announcementList != null) {
            this.announcementsView.showAnnouncements(announcementList.getAnnouncements());
        }
    }

    @Override
    public void loadAnnouncemntsFromRemote() {
        Call<AnnouncementList> announcementsListCall = hackCWRUServerCalls.getAnnouncementsFromServer();

        announcementsListCall.enqueue(new Callback<AnnouncementList>() {
            @Override
            public void onResponse(Call<AnnouncementList> call, Response<AnnouncementList> response) {
                AnnouncementList announcementList = response.body();
                announcementsView.showAnnouncements(announcementList.getAnnouncements());
                announcementsView.onRefreshFinish();
                localData.saveAnnouncementsToLocal(announcementList);
            }

            @Override
            public void onFailure(Call<AnnouncementList> call, Throwable t) {
                Log.d(LOG_TAG, "Failed to load announcements from server");
            }
        });
    }
}

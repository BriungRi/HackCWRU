package cwru.edu.hackcwru.announcements;

import java.util.List;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;
import cwru.edu.hackcwru.domain.Announcement;

public class AnnouncementsContract {
    interface View extends BaseView<Presenter>{
        void showAnnouncements(List<Announcement> announcements);

        void onRefreshFinish();

        void showNoAnnouncementsView();

        void hideNoAnnouncementsView();
    }

    interface Presenter extends BasePresenter{
        void setAnnouncementsView(AnnouncementsContract.View announcementsView);

        void loadAnnouncements();

        void loadAnnouncementsFromLocal();

        void loadAnnouncemntsFromRemote();
    }
}

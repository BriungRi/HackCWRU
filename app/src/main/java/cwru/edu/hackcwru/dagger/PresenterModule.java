package cwru.edu.hackcwru.dagger;

import javax.inject.Singleton;

import cwru.edu.hackcwru.announcements.AnnouncementsPresenter;
import cwru.edu.hackcwru.countdown.CountdownPresenter;
import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.events.EventsPresenter;
import cwru.edu.hackcwru.maps.MapsPresenter;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import dagger.Module;
import dagger.Provides;

@Module
class PresenterModule {

    @Provides
    @Singleton
    EventsPresenter providesEventsPresenter(LocalData localData, HackCWRUServerCalls hackCWRUServerCalls) {
        return new EventsPresenter(localData, hackCWRUServerCalls);
    }

    @Provides
    @Singleton
    CountdownPresenter providesCountdownPresenter(LocalData localData) {
        return new CountdownPresenter(localData);
    }

    @Provides
    @Singleton
    AnnouncementsPresenter providesAnnouncementsPresenter(LocalData localData, HackCWRUServerCalls hackCWRUServerCalls) {
        return new AnnouncementsPresenter(localData, hackCWRUServerCalls);
    }

    @Provides
    @Singleton
    MapsPresenter providesMapsPresenter(HackCWRUServerCalls hackCWRUServerCalls) {
        return new MapsPresenter(hackCWRUServerCalls);
    }
}

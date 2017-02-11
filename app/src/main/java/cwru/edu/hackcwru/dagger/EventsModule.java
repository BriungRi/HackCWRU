package cwru.edu.hackcwru.dagger;

import javax.inject.Singleton;

import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.events.EventsPresenter;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import dagger.Module;
import dagger.Provides;

@Module
public class EventsModule {

    @Provides
    @Singleton
    EventsPresenter providesEventsPresenter(LocalData localData, HackCWRUServerCalls hackCWRUServerCalls) {
        return new EventsPresenter(localData, hackCWRUServerCalls);
    }
}

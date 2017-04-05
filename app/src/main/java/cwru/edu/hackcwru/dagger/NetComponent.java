package cwru.edu.hackcwru.dagger;

import javax.inject.Singleton;

import cwru.edu.hackcwru.announcements.AnnouncementsFragment;
import cwru.edu.hackcwru.countdown.CountdownFragment;
import cwru.edu.hackcwru.eventdetail.EventDetailFragment;
import cwru.edu.hackcwru.events.EventsActivity;
import cwru.edu.hackcwru.events.EventsFragment;
import cwru.edu.hackcwru.maps.MapsFragment;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, PresenterModule.class, DataModule.class})
public interface NetComponent {
    void inject(EventsActivity activity);

    void inject(EventDetailFragment fragment);

    void inject(EventsFragment fragment);

    void inject(CountdownFragment fragment);

    void inject(AnnouncementsFragment fragment);

    void inject(MapsFragment fragmet);
}

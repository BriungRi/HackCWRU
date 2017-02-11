package cwru.edu.hackcwru.dagger;

import javax.inject.Singleton;

import cwru.edu.hackcwru.eventdetail.EventDetailFragment;
import cwru.edu.hackcwru.events.EventsActivity;
import cwru.edu.hackcwru.events.EventsFragment;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, EventsModule.class})
public interface NetComponent {
    void inject(EventsActivity activity);

    void inject(EventDetailFragment fragment);

    void inject(EventsFragment fragment);
    // void inject(MyService service);
}

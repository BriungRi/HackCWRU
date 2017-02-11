package cwru.edu.hackcwru;

import android.app.Application;

import cwru.edu.hackcwru.dagger.ApplicationModule;
import cwru.edu.hackcwru.dagger.DaggerNetComponent;
import cwru.edu.hackcwru.dagger.NetComponent;
import cwru.edu.hackcwru.dagger.NetModule;


public class HackCWRUApplication extends Application {
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return this.netComponent;
    }
}

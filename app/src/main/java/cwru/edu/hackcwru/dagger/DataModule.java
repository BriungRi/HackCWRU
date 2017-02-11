package cwru.edu.hackcwru.dagger;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import cwru.edu.hackcwru.data.LocalData;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    LocalData providesLocalData(SharedPreferences sharedPreferences){
        return new LocalData(sharedPreferences);
    }
}

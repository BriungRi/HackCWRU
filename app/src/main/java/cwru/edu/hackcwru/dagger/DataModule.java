package cwru.edu.hackcwru.dagger;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import cwru.edu.hackcwru.data.LocalData;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    LocalData providesLocalData(SharedPreferences sharedPreferences, Gson gson){
        return new LocalData(sharedPreferences, gson);
    }
}

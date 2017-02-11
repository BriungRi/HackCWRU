package cwru.edu.hackcwru.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.utils.StringConstants;

public class LocalData {

    private SharedPreferences sharedPreferences;

    public LocalData(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public EventList getEventsFromLocal(){
        String eventListJson = sharedPreferences.getString(StringConstants.SAVE_EVENTS_PREFERENCE, "");
        return new Gson().fromJson(eventListJson, EventList.class);
    }

    public void saveEventsToLocal(EventList eventsListToSave){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        Gson gson = new Gson();
        String eventListJson = gson.toJson(eventsListToSave);
        editor.putString(StringConstants.SAVE_EVENTS_PREFERENCE, eventListJson);
        editor.commit();
    }

    public AnnouncementsList getAnnouncementsFromLocal(){
        return null;
    }
}

package cwru.edu.hackcwru.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.utils.StringConstants;

public class LocalData {

    public static EventList getEventsFromLocal(SharedPreferences sharedPreferences){
        String eventListJson = sharedPreferences.getString(StringConstants.SAVE_EVENTS_PREFERENCE, "");
        return new Gson().fromJson(eventListJson, EventList.class);
    }

    public static void saveEventsToLocal(SharedPreferences sharedPreferences, EventList eventsListToSave){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String eventListJson = gson.toJson(eventsListToSave);
        editor.putString(StringConstants.SAVE_EVENTS_PREFERENCE, eventListJson);
        editor.commit();
    }

    public static AnnouncementsList getAnnouncementsFromLocal(){
        return null;
    }
}

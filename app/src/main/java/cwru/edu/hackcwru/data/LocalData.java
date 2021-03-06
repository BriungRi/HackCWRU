package cwru.edu.hackcwru.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import cwru.edu.hackcwru.domain.AnnouncementList;
import cwru.edu.hackcwru.domain.EventList;
import cwru.edu.hackcwru.domain.Map;
import cwru.edu.hackcwru.domain.MapList;
import cwru.edu.hackcwru.utils.StringConstants;

public class LocalData {

    private SharedPreferences sharedPreferences;

    private Gson gson;

    public LocalData(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public EventList getEventsFromLocal() {
        String eventListJson = sharedPreferences.getString(StringConstants.SAVE_EVENTS_PREFERENCE, "");
        return gson.fromJson(eventListJson, EventList.class);
    }

    public void saveEventsToLocal(EventList eventsToSave) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        String eventListJson = gson.toJson(eventsToSave);
        editor.putString(StringConstants.SAVE_EVENTS_PREFERENCE, eventListJson).apply();
    }

    public AnnouncementList getAnnouncementsFromLocal() {
        String announcementList = sharedPreferences.getString(StringConstants.SAVE_ANNOUNCEMENTS_PREFERENCE, "");
        return gson.fromJson(announcementList, AnnouncementList.class);
    }

    public void saveAnnouncementsToLocal(AnnouncementList announcementsToSave) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        String announcementsListJson = gson.toJson(announcementsToSave);
        editor.putString(StringConstants.SAVE_ANNOUNCEMENTS_PREFERENCE, announcementsListJson).apply();
    }

    public void saveDeviceToken(String token) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(StringConstants.SAVE_DEVICE_TOKEN_PREFERENCE, token).apply();
    }

    public String getDeviceToken() {
        String deviceToken = sharedPreferences.getString(StringConstants.SAVE_DEVICE_TOKEN_PREFERENCE, "");
        return deviceToken;
    }

    public void saveMapsToLocal(MapList mapList) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        String mapMetaDataAsString = gson.toJson(mapList);
        editor.putString(StringConstants.SAVE_MAP_DATA_TOKEN_PREFERENCE, mapMetaDataAsString).apply();
    }

    public MapList getMapListFromLocal() {
        String mapMetaDataAsString = sharedPreferences.getString(StringConstants.SAVE_MAP_DATA_TOKEN_PREFERENCE, null);
        return gson.fromJson(mapMetaDataAsString, MapList.class);
    }
}

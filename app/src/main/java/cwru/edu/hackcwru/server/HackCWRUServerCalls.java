package cwru.edu.hackcwru.server;

import cwru.edu.hackcwru.data.AnnouncementsList;
import cwru.edu.hackcwru.data.EventsList;
import cwru.edu.hackcwru.firebase.DeviceTokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HackCWRUServerCalls {

    String API_BASE_URL = "https://hack-cwru.com/api/v1/";

    @GET("events")
    Call<EventsList> getEventsFromServer();

    @GET("announcements")
    Call<AnnouncementsList> getAnnouncementsFromServer();

    @FormUrlEncoded
    @POST("notifications/register/{deviceToken}")
    Call<DeviceTokenResponse> registerDevice(@Field("apikey") String apikey,
                                             @Field("os") String operatingSystem,
                                             @Path("deviceToken") String deviceToken);
}

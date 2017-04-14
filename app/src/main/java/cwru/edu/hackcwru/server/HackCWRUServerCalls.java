package cwru.edu.hackcwru.server;

import cwru.edu.hackcwru.domain.AnnouncementList;
import cwru.edu.hackcwru.domain.EventList;
import cwru.edu.hackcwru.domain.MapList;
import cwru.edu.hackcwru.domain.Mentee;
import cwru.edu.hackcwru.domain.MentorRequestResponse;
import cwru.edu.hackcwru.firebase.DeviceTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HackCWRUServerCalls {
    String API_BASE_URL = "https://hack-cwru.com/api/v1/";

    @GET("events")
    Call<EventList> getEventsFromServer();

    @GET("announcements")
    Call<AnnouncementList> getAnnouncementsFromServer();

    @GET("maps")
    Call<MapList> getMapsFromServer();

    @FormUrlEncoded
    @POST("notification/recipients")
    Call<DeviceTokenResponse> registerDevice(@Field("apikey") String apikey,
                                             @Field("os") String operatingSystem,
                                             @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @POST("mentor/requests")
    Call<MentorRequestResponse> sendMentorRequest(@Field("apikey") String apikey,
                                                  @Field("locationDescription") String locationDescription,
                                                  @Field("topics") String topics,
                                                  @Field("mentee.name") String name,
                                                  @Field("mentee.deviceId") String deviceId);
}

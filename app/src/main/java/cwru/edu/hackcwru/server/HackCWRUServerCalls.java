package cwru.edu.hackcwru.server;

import cwru.edu.hackcwru.data.EventsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HackCWRUServerCalls {

    String API_BASE_URL = "https://hack-cwru.com/api/v1/";

    @GET("events")
    Call<EventsList> getEventsFromServer();
}

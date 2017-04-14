package cwru.edu.hackcwru.maps;

import java.util.List;

import cwru.edu.hackcwru.domain.Map;
import cwru.edu.hackcwru.domain.MapList;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsPresenter implements MapsContract.Presenter {
    private final String LOG_TAG = "MapsPresenter";

    private MapsContract.View mapsView;

    private HackCWRUServerCalls hackCWRUServerCalls;

    public MapsPresenter(HackCWRUServerCalls hackCWRUServerCalls) {
        this.hackCWRUServerCalls = hackCWRUServerCalls;
    }

    @Override
    public void start() {
        Log.d(LOG_TAG, "start()");
        Call<MapList> mapListCall = hackCWRUServerCalls.getMapsFromServer();
        mapListCall.enqueue(new Callback<MapList>() {
            @Override
            public void onResponse(Call<MapList> call, Response<MapList> response) {
                List<Map> mapList = response.body().getMaps();
                mapsView.loadMap(mapList.get(1).getImageURL());
                mapsView.setMapTitle(mapList.get(1).getName());
            }

            @Override
            public void onFailure(Call<MapList> call, Throwable t) {
                Log.d(LOG_TAG, "Map Server Call failed");
            }
        });
    }

    @Override
    public void setMapView(MapsContract.View view) {
        this.mapsView = view;
    }
}

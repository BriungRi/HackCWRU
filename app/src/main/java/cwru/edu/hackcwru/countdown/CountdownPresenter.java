package cwru.edu.hackcwru.countdown;

import java.util.List;

import cwru.edu.hackcwru.data.LocalData;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.domain.EventList;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountdownPresenter implements CountdownContract.Presenter {

    private CountdownContract.View countdownView;

    private LocalData localData;

    public CountdownPresenter(LocalData localData){
        this.localData = localData;
    }

    @Override
    public void start() {
        countdownView.displayCountdown();
    }

    @Override
    public void setCountdownView(CountdownContract.View countdownView) {
        this.countdownView = countdownView;
    }

    public List<Event> getAllEvents(){
        return localData.getEventsFromLocal().getEvents();
    }
}

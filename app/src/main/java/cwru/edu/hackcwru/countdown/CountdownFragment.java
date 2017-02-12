package cwru.edu.hackcwru.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.utils.Log;

public class CountdownFragment extends Fragment implements CountdownContract.View {
    private final String LOG_TAG = "Countdown Fragment";
    private List<Event> allEvents;
    private Event nextEvent,lastEvent;

    @Inject
    CountdownPresenter presenter;

    @BindView(R.id.countdown_view)
    TextView countdownView;
    @BindView(R.id.countdown_progress_bar)
    ProgressBar countdownProgress;
    @BindView(R.id.next_event_name)
    TextView nextEventName;
    @BindView(R.id.next_event_description)
    TextView nextEventDescription;
    @BindView(R.id.next_event_time)
    TextView nextEventTime;

    private Unbinder unbinder;

    public CountdownFragment() {
        // Empty Constructor
    }

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HackCWRUApplication) getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.countdown_fragment, container, false);
        this.unbinder = ButterKnife.bind(this, rootView);
        this.presenter.setCountdownView(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
        this.presenter.setCountdownView(null);
    }

    @Override
    public void displayCountdown() {
        allEvents=this.presenter.getAllEvents();
        getNextAndLastEvents();

        long currentTime = getEpochFromDateTime(lastEvent.getStartDateTime());
        final long lastEventStartTime = getEpochFromDateTime(lastEvent.getStartDateTime());
        final long nextEventStartTime = getEpochFromDateTime(nextEvent.getStartDateTime());
        final CountDownTimer countDownTimer = new CountDownTimer(nextEventStartTime - currentTime, 1000) {
            @Override
            public void onTick(long l) {
                if (countdownProgress != null && countdownView != null) {
                    String currentDateTimeString = formatEpoch(l);
                    int progress = (int) (100 * ((l * 1.0) / (nextEventStartTime-lastEventStartTime)));
                    Log.d(LOG_TAG,"Progress "+progress);
                    countdownProgress.setProgress(progress);
                    countdownView.setText(currentDateTimeString);
                }
            }

            @Override
            public void onFinish() {
                if (countdownProgress != null) {
                    countdownProgress.setProgress(100);
                }
            }
        };
        countDownTimer.start();
    }

    private String formatEpoch(long l) {
        int seconds = (int) (l / 1000) % 60;
        int minutes = (int) ((l / (1000 * 60)) % 60);
        int hours = (int) ((l / (1000 * 60 * 60)) % 24);
        return String.format("%02d", hours) + ":" +
                String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds);
    }

    private long getEpochFromDateTime(String time){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    private void getNextAndLastEvents(){
        long currentTime = System.currentTimeMillis();
        for(int j=0;j<allEvents.size();j++){
            Log.d(LOG_TAG,""+getEpochFromDateTime(allEvents.get(j).getStartDateTime())+"  "+currentTime);
            if(getEpochFromDateTime(allEvents.get(j).getStartDateTime())>currentTime){
                nextEvent=allEvents.get(j);
                if(j>0) {
                    lastEvent = allEvents.get(j - 1);
                }else{
                    lastEvent = allEvents.get(0);
                }
            }
            else{
                //TODO: these should be 0 but for testing keep them as 1 and 2
                nextEvent=allEvents.get(2);
                lastEvent=allEvents.get(1);
            }
        }
        nextEventName.setText(nextEvent.getName());
        nextEventDescription.setText(nextEvent.getDescription());
        nextEventTime.setText(nextEvent.getDateTimeToDisplay());
    }






}

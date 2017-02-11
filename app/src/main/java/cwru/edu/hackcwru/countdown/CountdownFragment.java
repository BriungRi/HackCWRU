package cwru.edu.hackcwru.countdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.utils.Log;

public class CountdownFragment extends Fragment implements CountdownContract.View {
    private final String LOG_TAG = "Countdown Fragment";

    CountdownContract.Presenter presenter;

    @BindView(R.id.countdown_view)
    TextView countdownView;
    @BindView(R.id.countdown_progress_bar)
    ProgressBar countdownProgress;

    public CountdownFragment() {
        // Empty Constructor
    }

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.countdown_fragment, container, false);
        ButterKnife.bind(this, rootView);

        displayCountdown();

        return rootView;
    }

    @Override
    public void displayCountdown() {

        long currentTime = 360000;
        final long nextEventStartTime = 720000;
        final CountDownTimer countDownTimer = new CountDownTimer(nextEventStartTime - currentTime, 1000) {
            @Override
            public void onTick(long l) {
                String currentDateTimeString = formatEpoch(l);
                //Actual logic will use current time so progress doesn't start at the same value every time
                int progress = (int) (100 * (((nextEventStartTime - l) * 1.0) / (nextEventStartTime)));
                countdownProgress.setProgress(progress);
                countdownView.setText(currentDateTimeString);
            }

            @Override
            public void onFinish() {
                countdownProgress.setProgress(100);
            }
        };
        countDownTimer.start();
    }

    public String formatEpoch(long l) {
        int seconds = (int) (l / 1000) % 60;
        int minutes = (int) ((l / (1000 * 60)) % 60);
        int hours = (int) ((l / (1000 * 60 * 60)) % 24);
        return String.format("%02d", hours) + ":" +
                String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds);
    }
}

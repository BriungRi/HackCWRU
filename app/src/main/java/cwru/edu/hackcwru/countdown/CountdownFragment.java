package cwru.edu.hackcwru.countdown;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;

public class CountdownFragment extends Fragment {
    private final String LOG_TAG = "Countdown Fragment";

    @BindView(R.id.countdown_view)
    TextView countdownView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.countdown_fragment, container, false);
        ButterKnife.bind(this, rootView);

        final CountDownTimer countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long l) {
                String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                countdownView.setText(currentDateTimeString);
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

        return rootView;
    }
}

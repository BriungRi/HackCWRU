package cwru.edu.hackcwru.countdown;

import cwru.edu.hackcwru.data.LocalData;

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
}

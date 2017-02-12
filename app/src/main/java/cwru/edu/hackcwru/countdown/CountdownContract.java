package cwru.edu.hackcwru.countdown;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;

public class CountdownContract {
    interface View extends BaseView<Presenter>{
        void displayCountdown();
    }

    interface Presenter extends BasePresenter{
        void setCountdownView(CountdownContract.View countdownView);
    }
}

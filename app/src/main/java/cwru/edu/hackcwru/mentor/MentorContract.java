package cwru.edu.hackcwru.mentor;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;

public class MentorContract {

    interface View extends BaseView<MentorContract.Presenter>{
        void clearFields();

        void showSuccessSnackbar();

        void showFailureToast();

        void hideKeyboard();


    }

    interface Presenter extends BasePresenter{
        void setMentorView(MentorContract.View view);

        void requestMentor(String apikey, String name, String topics, String location);
    }
}

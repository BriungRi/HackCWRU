package cwru.edu.hackcwru.map;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;

/**
 * Created by anna.sedlackova on 2/2/2017.
 */

public class MapContract {
    interface View extends BaseView<Presenter>{
        void displayMap();
    }

    interface Presenter extends BasePresenter{

    }
}

package cwru.edu.hackcwru.maps;

import cwru.edu.hackcwru.BasePresenter;
import cwru.edu.hackcwru.BaseView;

public interface MapsContract {
    interface View extends BaseView<Presenter> {
        void loadMap(String imageUrl);
    }

    interface Presenter extends BasePresenter {
        void setMapView(MapsContract.View view);
    }
}

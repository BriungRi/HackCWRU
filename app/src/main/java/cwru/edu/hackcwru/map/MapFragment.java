package cwru.edu.hackcwru.map;

/**
 * Created by anna.sedlackova on 2/2/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;

public class MapFragment extends Fragment {

    //constructor
    public MapFragment(){

    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Nullable
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, rootView);

        //displayMap();

        return rootView;
    }

    //@Override
    //public void setPresenter(MapContract.Presenter presenter) {
       // this.presenter = presenter;
   // }

}


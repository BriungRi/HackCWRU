package cwru.edu.hackcwru.maps;

import android.os.Bundle;

import cwru.edu.hackcwru.R;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import cwru.edu.hackcwru.HackCWRUApplication;

public class MapsFragment extends Fragment implements MapsContract.View {

    @Inject
    MapsPresenter presenter;

    @BindView(R.id.map_image_view)
    ImageView mapView;

    public MapsFragment() {
        // Empty Constructor
    }

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HackCWRUApplication) getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        presenter.setMapView(this);
        return rootView;
    }

    @Override
    public void loadMap(String imageUrl) {
        Picasso.with(getContext()).load(imageUrl).into(mapView);
    }
}

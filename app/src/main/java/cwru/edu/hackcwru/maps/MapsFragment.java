package cwru.edu.hackcwru.maps;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cwru.edu.hackcwru.R;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.utils.Log;

public class MapsFragment extends Fragment implements MapsContract.View {

    private static final String LOG_TAG = "MapsFragment";

    private Unbinder unbinder;

    @Inject
    MapsPresenter presenter;

    @BindView(R.id.maps_image_view)
    ImageView mapsView;
    @BindView(R.id.maps_title)
    TextView mapsTextView;

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

        unbinder = ButterKnife.bind(this, rootView);

        presenter.setMapView(this);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        this.presenter.setMapView(null);
    }

    @Override
    public void loadMap(String imageUrl) {
        Log.d(LOG_TAG, imageUrl);
        Picasso.with(getContext()).load(imageUrl).into(mapsView);
    }

    @Override
    public void setMapTitle(String mapTitle) {
        this.mapsTextView.setText(mapTitle);
    }
}

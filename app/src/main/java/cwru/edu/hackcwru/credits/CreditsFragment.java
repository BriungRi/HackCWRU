package cwru.edu.hackcwru.credits;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cwru.edu.hackcwru.R;


public class CreditsFragment extends Fragment {

    public CreditsFragment() {
        // Empty Constructor
    }

    public static CreditsFragment newInstance() {
        return new CreditsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.credit_fragment, container, false);

        return rootView;
    }
}

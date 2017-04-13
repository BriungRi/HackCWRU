package cwru.edu.hackcwru.mentor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.utils.StringUtils;
import cwru.edu.hackcwru.utils.UIUtils;

public class MentorFragment extends Fragment implements MentorContract.View {
    @Inject
    MentorPresenter presenter;

    Unbinder unbinder;

    @BindView(R.id.name_edit_text)
    EditText nameEditText;
    @BindView(R.id.topics_edit_text)
    EditText topicsEditText;
    @BindView(R.id.location_edit_text)
    EditText locationEditText;

    @OnClick(R.id.request_mentor_button)
    public void requestMentor() {
        String name = nameEditText.getText().toString();
        String topics = topicsEditText.getText().toString();
        String location = locationEditText.getText().toString();

        if (StringUtils.isEmpty(name, topics, location)) {
            UIUtils.toast(getContext(), "Please complete all fields.");
        } else {
            String apikey = getString(R.string.HACKCWRU_API_KEY);
            presenter.requestMentor(apikey, name, topics, location);
        }
    }

    public MentorFragment() {
        // Empty Constructor
    }

    public static MentorFragment newInstance() {
        return new MentorFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HackCWRUApplication) getActivity().getApplication()).getNetComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mentor_fragment, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        presenter.setMentorView(this);

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
    }

    @Override
    public void clearFields() {
        this.nameEditText.setText("");
        this.topicsEditText.setText("");
        this.locationEditText.setText("");
    }

    @Override
    public void showProgressDialog() {

    }
}

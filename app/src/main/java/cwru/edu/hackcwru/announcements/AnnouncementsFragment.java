package cwru.edu.hackcwru.announcements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Announcement;
import cwru.edu.hackcwru.data.AnnouncementsList;
import cwru.edu.hackcwru.server.HackCWRUServerCalls;
import cwru.edu.hackcwru.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;


public class AnnouncementsFragment extends Fragment {
    private final String LOG_TAG = "AnnouncementsFragment";

    @BindView(R.id.announcements_list)
    RecyclerView announcementsList;
    private AnnouncementListAdapter announcementListAdapter;

    @BindView(R.id.no_announcements_text)
    TextView noAnnouncementsText;

    public AnnouncementsFragment(){
        // Empty Constructor
    }

    public static AnnouncementsFragment newInstance(){
        return new AnnouncementsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.announcements_fragment, container, false);
        ButterKnife.bind(this, rootView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HackCWRUServerCalls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HackCWRUServerCalls hackCWRUServerCalls = retrofit.create(HackCWRUServerCalls.class);

        Call<AnnouncementsList> loadAnnouncementsCall = hackCWRUServerCalls.getAnnouncementsFromServer();
        loadAnnouncementsCall.enqueue(new Callback<AnnouncementsList>() {
            @Override
            public void onResponse(Call<AnnouncementsList> call, Response<AnnouncementsList> response) {
                List<Announcement> announcements = response.body().getAnnouncements();

                Log.d(LOG_TAG, response.body().toString());
                announcementListAdapter = new AnnouncementListAdapter(announcements);
                announcementsList.setHasFixedSize(true);
                announcementsList.setLayoutManager(new LinearLayoutManager(getActivity()));
                announcementsList.setAdapter(announcementListAdapter);
                announcementsList.addItemDecoration(new AnnouncementsFragment.SimpleDividerItemDecoration(getActivity()));

                if(announcements.isEmpty())
                    noAnnouncementsText.setVisibility(View.VISIBLE);
                else
                    noAnnouncementsText.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AnnouncementsList> call, Throwable t) {
                Log.d(LOG_TAG, "getAnnouncementsFromServer() call failed");
            }
        });

        return rootView;
    }

    public class AnnouncementListAdapter extends RecyclerView.Adapter<AnnouncementsFragment.AnnouncementListAdapter.ViewHolder> {
        private List<Announcement> announcements;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            @BindView(R.id.list_announcement_title)
            TextView announcementTitle;
            @BindView(R.id.list_announcement_message)
            TextView announcementMessage;
            @BindView(R.id.list_announcement_updatedAt_time)
            TextView announcementUpdatedAt;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        public AnnouncementListAdapter(List<Announcement> announcements) {
            setAnnouncements(announcements);
        }

        private void setAnnouncements(List<Announcement> announcements) {
            this.announcements = checkNotNull(announcements);
//            notifyDataSetChanged();
        }

        @Override
        public AnnouncementsFragment.AnnouncementListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.announcement_item, parent, false);
            return new AnnouncementsFragment.AnnouncementListAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final AnnouncementsFragment.AnnouncementListAdapter.ViewHolder holder, final int position) {
            final Announcement event = announcements.get(position);

            holder.announcementTitle.setText(event.getTitle());
            holder.announcementUpdatedAt.setText(event.getUpdatedAt());
            holder.announcementMessage.setText(event.getMessage());
        }

        @Override
        public int getItemCount() {
            return announcements.size();
        }
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = ResourcesCompat.getDrawable(getResources(), R.drawable.line_divider, null);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}

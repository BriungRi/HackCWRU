package cwru.edu.hackcwru.events;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.data.Event;

import static com.google.common.base.Preconditions.checkNotNull;

public class EventsFragment extends Fragment implements EventsContract.View {
    private final String LOG_TAG = "EventsFragment";

    private EventsContract.Presenter presenter;

    @BindView(R.id.event_list)
    RecyclerView eventList;
    private EventListAdapter eventListAdapter;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    EventItemListener eventItemListener = new EventItemListener() {
        @Override
        public void onTaskClick(Event clickedEvent) {
            presenter.openEventDetails(clickedEvent);
        }

        @Override
        public void onTaskSave(Event savedEvent) {
            presenter.saveEvent(savedEvent);
        }
    };

    public EventsFragment() {
        // Empty Constructor
    }

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventListAdapter = new EventListAdapter(new ArrayList(), eventItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_fragment, container, false);
        ButterKnife.bind(this, rootView);

        eventList.setHasFixedSize(true);
        eventList.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventList.setAdapter(eventListAdapter);
        eventList.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        swipeRefreshLayout.setScrollUpChild(eventList);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadEvents());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(@NonNull EventsContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showEvents(List<Event> events) {
        eventListAdapter.replaceEvents(events);
    }

    @Override
    public void onRefreshFinish() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
        else if (getView() != null) {
            final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
        }
    }

    public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
        private List<Event> events;
        private EventItemListener eventItemListener;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            @BindView(R.id.list_event_name)
            TextView eventName;
            @BindView(R.id.list_event_description)
            TextView eventDescription;
            @BindView(R.id.list_event_time)
            TextView eventTime;
            @BindView(R.id.save_button)
            ImageView saveButton;
            @BindView(R.id.list_item_view)
            View item;
            boolean saved;

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public EventListAdapter(List<Event> events, EventItemListener eventItemListener) {
            setEvents(events);
            this.eventItemListener = eventItemListener;
        }

        public void replaceEvents(List<Event> events) {
            setEvents(events);
            notifyDataSetChanged();
        }

        private void setEvents(List<Event> events) {
            this.events = checkNotNull(events);
        }

        // Create new views (invoked by the layout manager)
        @Override
        public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item, parent, false);
            return new ViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Event event = events.get(position);

            holder.eventName.setText(event.getName());
            holder.eventTime.setText(event.getDateTimeToDisplay());
            holder.eventDescription.setText(event.getDescription());

            if (event.isSaved()) {
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
            } else {
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
            }

            holder.item.setOnClickListener(view -> eventItemListener.onTaskClick(event)
            );

            holder.saveButton.setOnClickListener(view -> {
                        if (event.isSaved()) {
                            holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                        } else {
                            holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
                        }
                        eventItemListener.onTaskSave(event);
                    }
            );
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return events.size();
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

    public interface EventItemListener {

        void onTaskClick(Event clickedEvent);

        void onTaskSave(Event savedEvent);
    }
}

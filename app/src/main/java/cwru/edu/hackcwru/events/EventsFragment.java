package cwru.edu.hackcwru.events;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cwru.edu.hackcwru.HackCWRUApplication;
import cwru.edu.hackcwru.R;
import cwru.edu.hackcwru.domain.Event;
import cwru.edu.hackcwru.ui.ScrollChildSwipeRefreshLayout;
import cwru.edu.hackcwru.utils.Log;
import cwru.edu.hackcwru.utils.TimeUtils;
import cwru.edu.hackcwru.utils.UIUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class EventsFragment extends Fragment implements EventsContract.View {
    private final String LOG_TAG = "EventsFragment";

    @Inject
    EventsPresenter presenter;

    private Unbinder unbinder;

    @BindView(R.id.event_list)
    RecyclerView eventList;
    private EventListAdapter eventListAdapter;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.no_events_text)
    TextView noEventsTextView;

    @BindView(R.id.save_floating_action_button)
    FloatingActionButton showBookMarkedItemsButton;

    @OnClick(R.id.save_floating_action_button)
    public void showSavedEvents() {
        presenter.bookmarkButtonPerformClick();
    }

    EventItemListener eventItemListener = new EventItemListener() {
        @Override
        public void onTaskClick(Event clickedEvent) {
            presenter.openEventDetails(clickedEvent);
        }

        @Override
        public void onTaskSave(Event savedEvent) {
            Log.d(LOG_TAG, savedEvent.getId());
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
        ((HackCWRUApplication) getActivity().getApplication()).getNetComponent().inject(this);
        eventListAdapter = new EventListAdapter(new ArrayList<Event>(), eventItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_fragment, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        presenter.setEventsView(this);

        eventList.setHasFixedSize(true);
        eventList.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventList.setAdapter(eventListAdapter);
        eventList.addItemDecoration(new SimpleDividerItemDecoration());

        swipeRefreshLayout.setScrollUpChild(eventList);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadEvents();
            }
        });

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
        presenter.setEventsView(null);
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
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void showNoEventsText() {
        noEventsTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoEventsText() {
        noEventsTextView.setVisibility(View.GONE);
    }

    @Override
    public void showOnEventSavedSnackback(String message) {
        // TODO: Fix Floating Action Button Problem
//        UIUtils.showSnackBar(showBookMarkedItemsButton, message);
        UIUtils.toast(getActivity(), message);
    }

    @Override
    public void updateBookmarkButtonBackgroundResource(int resourceId) {
        showBookMarkedItemsButton.setImageResource(resourceId);
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

            public ViewHolder(View v) {
                super(v);
                ButterKnife.bind(this, v);
            }
        }

        public EventListAdapter(List<Event> events, EventItemListener eventItemListener) {
            setEvents(events);
            this.eventItemListener = eventItemListener;
        }

        public void replaceEvents(List<Event> events) {
            setEvents(events);
        }

        private void setEvents(List<Event> events) {
            this.events = checkNotNull(events);
            notifyDataSetChanged();
        }

        @Override
        public EventListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Event event = events.get(position);

            holder.eventName.setText(event.getName());
            holder.eventTime.setText(event.getPrettyStartDateTime());
            holder.eventDescription.setText(event.getDescription());

            if (event.isSaved()) {
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
            } else {
                holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
            }

            holder.item.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   eventItemListener.onTaskClick(event);
                                               }
                                           }
            );

            holder.saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (event.isSaved()) {
                        holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                        event.setSaved(false);
                    } else {
                        holder.saveButton.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
                        event.setSaved(true);
                    }
                    eventItemListener.onTaskSave(event);
                }
            });
        }

        @Override
        public int getItemCount() {
            return events.size();
        }
    }

    private class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration() {
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

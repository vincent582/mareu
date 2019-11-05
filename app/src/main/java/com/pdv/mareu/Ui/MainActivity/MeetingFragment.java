package com.pdv.mareu.Ui.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pdv.mareu.Base.BaseActivity;
import com.pdv.mareu.Event.DeleteMeetingEvent;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingRepository = ((BaseActivity) getActivity()).getMeetingRepository();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.meeting_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initList();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.sort_by_date_item:
                mMeetingRepository.sortByDate();
                initList();
                Toast.makeText(getActivity(),"Les réunions ont été triées par date",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sort_by_place_item:
                mMeetingRepository.sortByPlace();
                initList();
                Toast.makeText(getContext(),"les réunions ont été triées par salle",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initList() {
        List<Meeting> mMeetings = mMeetingRepository.getMeetingsList();
        mRecyclerView.setAdapter(new MeetingItemRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mMeetingRepository.deleteMeeting(event.mMeeting);
        initList();
    }
}



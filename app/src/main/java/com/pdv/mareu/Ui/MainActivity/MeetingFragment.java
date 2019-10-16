package com.pdv.mareu.Ui.MainActivity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdv.mareu.DI.DI;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    private MeetingRepository mMeetingRepository;
    private RecyclerView.Adapter adapter;
    private RecyclerView mRecyclerView;

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingRepository = ((MainActivity)getActivity()).getMeetingRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_meeting_recycler_view);
        adapter = new MeetingItemRecyclerViewAdapter(mMeetingRepository.getMeetingsList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(this.adapter);
        return view;
    }
}



package com.pdv.mareu.Ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdv.mareu.DI.DI;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Service.MeetingApiService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    private MeetingApiService mApiService;
    private RecyclerView.Adapter adapter;
    private List<Meeting> mMeetingList;
    private RecyclerView mRecyclerView;

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.item_meeting_recycler_view);
        this.mMeetingList = mApiService.getMeetingList();
        this.adapter = new MeetingItemRecyclerViewAdapter(this.mMeetingList);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter.notifyDataSetChanged();
        this.mRecyclerView.setAdapter(this.adapter);

        return view;
    }
}



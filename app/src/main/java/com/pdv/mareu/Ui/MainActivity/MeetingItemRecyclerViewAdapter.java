package com.pdv.mareu.Ui.MainActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;

import java.util.List;

public class MeetingItemRecyclerViewAdapter extends RecyclerView.Adapter<MeetingItemViewHolder>{

    public List<Meeting> mMeetingList;

    // CONSTRUCTOR
    public MeetingItemRecyclerViewAdapter(List<Meeting> meetingList) {
        this.mMeetingList = meetingList;
    }

    @NonNull
    @Override
    public MeetingItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meeting_item, viewGroup, false);
        return new MeetingItemViewHolder(view);
    }

    public void updateList(List<Meeting> meetingList){
        this.mMeetingList = meetingList;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingItemViewHolder meetingItemViewHolder, final int i) {
        meetingItemViewHolder.updateWithMeeting(this.mMeetingList.get(i));
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }
}
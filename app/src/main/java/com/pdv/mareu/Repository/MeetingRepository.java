package com.pdv.mareu.Repository;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.ApiService.MeetingApiService;

import java.util.List;

public class MeetingRepository {

    private final MeetingApiService mMeetingApiService;

    public MeetingRepository(MeetingApiService meetingApiService) {
        mMeetingApiService = meetingApiService;
    }

    public List<Meeting> getMeetingsList(){
        List<Meeting> meetings = mMeetingApiService.getMeetingList();
        return meetings;
    }

    public void addMeeting(Meeting meeting){
        mMeetingApiService.addMeeting(meeting);
    }
}

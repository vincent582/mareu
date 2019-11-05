package com.pdv.mareu.Repository;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.Model.Room;

import java.util.List;

public class MeetingRepository {

    private final MeetingApiService mMeetingApiService;

    public MeetingRepository(MeetingApiService meetingApiService) { mMeetingApiService = meetingApiService; }

    public List<Meeting> getMeetingsList(){
        List<Meeting> meetings = mMeetingApiService.getMeetingList();
        return meetings;
    }

    public void addMeeting(Meeting meeting){
        mMeetingApiService.addMeeting(meeting);
    }

    public void deleteMeeting(Meeting meeting){
        mMeetingApiService.deleteMeeting(meeting);
    }

    public List<Room> getMeetingsRoomsList(){
        List<Room> roomList = mMeetingApiService.getRooms();
        return roomList;
    }

    public void sortByDate(){ mMeetingApiService.sortByDate(); }

    public void sortByPlace(){
        mMeetingApiService.sortByPlace();
    }
}

package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiService implements MyMeetingApiService {

    private List<Meeting> mMeetingList = MeetingGeneratorApi.generateMeetings();
    private List<Room> mRoomList = MeetingGeneratorApi.generateRooms();

    @Override
    public List<Meeting> getMeetingList() {
        return this.mMeetingList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    @Override
    public List<Room> getRooms() {
        return this.mRoomList;
    }

    @Override
    public List<Meeting> filterByDate(String date) {
        List<Meeting> meetingListByDate = new ArrayList<>();
        for (Meeting meeting: mMeetingList) {
            if (meeting.getDateFormated().equals(date)){
                meetingListByDate.add(meeting);
            }
        }
        return meetingListByDate;
    }

    @Override
    public List<Meeting> filterByPlace(String place) {
        List<Meeting> meetingListByPlace = new ArrayList<>();
        for (Meeting meeting: mMeetingList) {
            if (meeting.getRoom().toString() == place){
                meetingListByPlace.add(meeting);
            }
        }
        return meetingListByPlace;
    }
}

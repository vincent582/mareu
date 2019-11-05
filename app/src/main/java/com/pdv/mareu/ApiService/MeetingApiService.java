package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MeetingApiService implements MyMeetingApiService {

    private List<Meeting> mMeetingList = MeetingGeneratorApi.generateMeeting();
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
    public void sortByDate() {
     Collections.sort(mMeetingList, new Comparator<Meeting>() {
         @Override
         public int compare(Meeting o1, Meeting o2) {
             return o1.getDate().compareToIgnoreCase(o2.getDate());
         }
     });
    }

    @Override
    public void sortByPlace() {
        Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return String.valueOf(o1.getRoom().getName()).compareToIgnoreCase(String.valueOf(o2.getRoom().getName()));
            }
        });
    }
}

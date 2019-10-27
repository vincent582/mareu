package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
    public void sortByHour() {
        Collections.sort(mMeetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return Integer.valueOf(o1.getHour()).compareTo(Integer.valueOf(o2.getHour()));
            }
            @Override
            public boolean equals(Object obj) {
                return false;
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


    @Override
    public boolean checkIfRoomIsAvailableAtTime(Room room, int hour, int minute ) {
        List<Meeting> meetings = room.getMeetingList();
        for (Meeting meeting: meetings) {
            int meetingMinuteEnd = meeting.getMinute() + 45;
            int meetingHourEnd = 0;
            if (meetingMinuteEnd > 59){
                meetingMinuteEnd -= 60;
                meetingHourEnd = hour + 1;
                if (meetingHourEnd > 23){
                    meetingHourEnd = 0;
                }else {
                    meetingHourEnd = meeting.getHour();
                }
            }
            if (hour >= meeting.getHour() && hour <= meetingHourEnd){
                if (minute >= meeting.getMinute() && minute <= meetingMinuteEnd){
                    return false;
                }
            }
        }
        return true;
    }
}

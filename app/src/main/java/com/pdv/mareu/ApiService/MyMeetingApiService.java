package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.List;

public interface MyMeetingApiService {

    List<Meeting> getMeetingList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Room> getRooms();

    List<Meeting> sortByDate(String date);

    List<Meeting> sortByPlace(String place);
}

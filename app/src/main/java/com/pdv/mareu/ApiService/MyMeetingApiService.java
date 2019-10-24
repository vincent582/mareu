package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.List;

public interface MyMeetingApiService {

    List<Meeting> getMeetingList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Room> getRooms();

    boolean checkIfRoomIsAvailableAtTime(Room room, int hour, int minute );
}

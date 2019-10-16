package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;

import java.util.List;

public interface MyMeetingApiService {

    List<Meeting> getMeetingList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);
}

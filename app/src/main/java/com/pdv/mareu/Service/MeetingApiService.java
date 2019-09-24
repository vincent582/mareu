package com.pdv.mareu.Service;

import com.pdv.mareu.Model.Meeting;
import java.util.List;

public class MeetingApiService implements MyMeetingApiService {

    private List<Meeting> mMeetingList = MeetingGeneratorApi.generateMeeting();

    @Override
    public List<Meeting> getMeetingList() {
        return this.mMeetingList;
    }
}

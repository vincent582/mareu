package com.pdv.mareu.Utils.Event;

import com.pdv.mareu.Model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting){
        this.mMeeting = meeting;
    }
}

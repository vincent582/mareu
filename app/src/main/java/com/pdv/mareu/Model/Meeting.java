package com.pdv.mareu.Model;

import java.util.List;

public class Meeting {

    private String mDate;
    private Room room;
    private String subject;
    private List<String> mailContributor;
    private String mTime;

    public Meeting(String date, Room room, String subject, List<String> mailContributor,String time) {
        this.room = room;
        this.subject = subject;
        this.mailContributor = mailContributor;
        this.mDate = date;
        this.mTime = time;
    }

    public Room getRoom() {
        return room;
    }
    public String getSubject() {
        return subject;
    }
    public List<String> getMailContributor() {
        return mailContributor;
    }
    public String getDate() { return mDate; }
    public String getTime() { return mTime; }
}

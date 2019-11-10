package com.pdv.mareu.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Meeting {

    private Date mDate;
    private Room room;
    private String subject;
    private List<String> mailContributor;
    private String mTime;

    public Meeting(Date date, Room room, String subject, List<String> mailContributor, String time) {
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
    public Date getDate() { return mDate; }
    public String getTime() { return mTime; }

    public String getDateFormated(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(this.getDate());
        return dateString;
    }
}

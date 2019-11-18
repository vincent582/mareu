package com.pdv.mareu.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Meeting {

    private Date mDate;
    private Room room;
    private String subject;
    private List<String> mailContributor;

    public Meeting(Date date, Room room, String subject, List<String> mailContributor) {
        this.room = room;
        this.subject = subject;
        this.mailContributor = mailContributor;
        this.mDate = date;
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

    public String getDateFormated(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(mDate);
        return dateString;
    }

    public StringBuilder getTimeFormated(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeString = sdf.format(this.getDate());
        StringBuilder time = new StringBuilder(timeString);
        time.setCharAt(2, 'H');
        return time;
    }
}

package com.pdv.mareu.Model;

import java.util.List;

public class Meeting {

    private int hour;
    private int minute;
    private Room room;
    private String subject;
    private List<String> mailContributor;

    public Meeting(Room room, String subject, List<String> mailContributor,int hour,int minute) {
        this.hour = hour;
        this.minute = minute;
        this.room = room;
        this.subject = subject;
        this.mailContributor = mailContributor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getMailContributor() {
        return mailContributor;
    }

    public void setMailContributor(List<String> mailContributor) {
        this.mailContributor = mailContributor;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTime(){
        return String.format("%02dh%02d", hour, minute);
    }
}

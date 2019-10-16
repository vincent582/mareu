package com.pdv.mareu.Model;

import java.util.List;

public class Meeting {

    private String time;
    private String room;
    private String subject;
    private List<String> mailContributor;

    public Meeting(String subject, String room, List<String> mailContributor) {
        this.time = "13h30";
        this.room = room;
        this.subject = subject;
        this.mailContributor = mailContributor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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

}

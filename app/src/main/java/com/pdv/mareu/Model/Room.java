package com.pdv.mareu.Model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private Boolean isAvailable;
    private List<Meeting> mMeetingList = new ArrayList<>();

    public Room(String name, Boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public List<Meeting> getMeetingList() {
        return mMeetingList;
    }
}

package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class MeetingGeneratorApi {

    /**
     * List of Rooms
     */
    public static List<Room> ROOM_LIST = Arrays.asList(
            new Room("Salle 1","#E9D0C6"),
            new Room("Salle 2","#9ABCA4"),
            new Room("Salle 3","#E9D0C6"),
            new Room("Salle 4","#9ABCA4"),
            new Room("Salle 5","#E9D0C6"),
            new Room("Salle 6","#9ABCA4"),
            new Room("Salle 7","#E9D0C6"),
            new Room("Salle 8","#9ABCA4"),
            new Room("Salle 9","#E9D0C6"),
            new Room("Salle 10","#9ABCA4")
    );

    /**
     * List of Meetings
     */
    public static List<Meeting> MEETINGS = Arrays.asList();

    /**
     * Generate list of meetings we will use for app
     * @return ArrayList of Meetings
     */
    static List<Meeting> generateMeetings() { return new ArrayList<>(MEETINGS);}

    /**
     * Generate list of rooms
     * @return new ROOM_LIST ArrayList
     */
    static List<Room> generateRooms(){ return new ArrayList<>(ROOM_LIST);}


    /*
    *########################################################
    *###  CREATE FAKE_MEETING FOR TEST ###
    *########################################################
    */

    public static Calendar mCalendar = Calendar.getInstance();
    public static Date mDate = mCalendar.getTime();
    public static Date mDate2 = oneHourMoreToDate();

    public static Date oneHourMoreToDate(){
        mCalendar.add(Calendar.DATE,2);
        mDate2 = mCalendar.getTime();
        return mDate2;
    }

    public static List<String> MAILS = Arrays.asList(
            "Mario@gmail.com",
            "Luigi@gmail.com",
            "peach@gmail.com"
    );
    public static List<Meeting> FAKE_MEETING = Arrays.asList(
            new Meeting(mDate,ROOM_LIST.get(7),"Réunion 1", MAILS),
            new Meeting(mDate2,ROOM_LIST.get(1),"Réunion 2", MAILS)
    );
}

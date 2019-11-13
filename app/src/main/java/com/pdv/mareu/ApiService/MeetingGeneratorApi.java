package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;
import com.pdv.mareu.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class MeetingGeneratorApi {

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

    public static Date mDate = Calendar.getInstance().getTime();
    public static List<String> MAILS = Arrays.asList(
            "Mario@gmail.com",
            "Luigi@gmail.com",
            "peach@gmail.com"
    );
    public static List<Meeting> FAKE_MEETING = Arrays.asList(
        new Meeting(mDate,ROOM_LIST.get(1),"Réunion 1", MAILS),
        new Meeting(mDate,ROOM_LIST.get(4),"Réunion 2", MAILS)
    );

    public static List<Meeting> MEETINGS = Arrays.asList();

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }
    static List<Room> generateRooms(){ return new ArrayList<>(ROOM_LIST);}
}

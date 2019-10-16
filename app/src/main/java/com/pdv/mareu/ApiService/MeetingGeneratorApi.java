package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGeneratorApi {

    public static List<String> MAILS = Arrays.asList(
            "Mario@gmail.com",
            "Luigi@gmail.com",
            "peach@gmail.com"
    );

    public static List<Room> ROOM_LIST = Arrays.asList(
            new Room("Salle 1",true),
            new Room("Salle 2",true),
            new Room("Salle 3",true),
            new Room("Salle 4",true),
            new Room("Salle 5",true),
            new Room("Salle 6",true),
            new Room("Salle 7",true),
            new Room("Salle 8",true),
            new Room("Salle 9",true),
            new Room("Salle 10",true)
    );

    public static List<Meeting> FAKE_MEETING = Arrays.asList(
            new Meeting("Peach","Réunion A",MAILS,"13h30"),
            new Meeting("Mario","Réunion B",MAILS,"15h30"),
            new Meeting(ROOM_LIST.get(4).getName(),"Réunion C",MAILS,"18h00")
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(FAKE_MEETING);
    }
}

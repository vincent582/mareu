package com.pdv.mareu.ApiService;

import com.pdv.mareu.Model.Meeting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGeneratorApi {

    public static List<String> MAILS = Arrays.asList(
            "Mario@gmail.com",
            "Luigi@gmail.com",
            "peach@gmail.com"
    );

    public static List<Meeting> FAKE_MEETING = Arrays.asList(
            new Meeting("Peach","Réunion A",MAILS),
            new Meeting("Mario","Réunion B",MAILS),
            new Meeting("Luigi","Réunion C",MAILS)
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(FAKE_MEETING);
    }
}

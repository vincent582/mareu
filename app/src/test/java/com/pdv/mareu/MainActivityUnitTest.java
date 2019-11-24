package com.pdv.mareu;

import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Repository.MeetingRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.pdv.mareu.ApiService.MeetingGeneratorApi.MAILS;
import static com.pdv.mareu.ApiService.MeetingGeneratorApi.ROOM_LIST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainActivityUnitTest {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetingList;
    private Calendar mCalendar = Calendar.getInstance();
    private Date currentTime = mCalendar.getTime();

    @Before
    public void setup() {
        mMeetingRepository = new MeetingRepository(new MeetingApiService());
        mMeetingList = mMeetingRepository.getMeetingsList();
    }

    /**
     * Create and Add a meeting then check if List of meeting contains it.
     */
    @Test
    public void addMeetingWithSuccess(){
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(1),"Réunion Test", MAILS);
        mMeetingRepository.addMeeting(mMeeting);
        assertTrue(mMeetingList.contains(mMeeting));
    }

    /**
     * Create and Add a meeting, check if List of meeting contains it.
     * Then delete the meeting and check than is not contains anymore.
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(1),"Réunion Test", MAILS);
        mMeetingRepository.addMeeting(mMeeting);
        assertTrue(mMeetingList.contains(mMeeting));
        mMeetingRepository.deleteMeeting(mMeeting);
        assertFalse(mMeetingList.contains(mMeeting));
    }


    /**
     * Filter list by place.
     * And check if meetings filtered have room expected.
     */
    @Test
    public void sortByPlaceMeeting(){
        String placeFilter = "Salle 1";//ROOM_LIST id = 0.
        Meeting m1 = new Meeting(currentTime,ROOM_LIST.get(0),"Réunion Test", MAILS);
        Meeting m2 = new Meeting(currentTime,ROOM_LIST.get(5),"Réunion Test", MAILS);
        Meeting m3 = new Meeting(currentTime,ROOM_LIST.get(0),"Réunion Test", MAILS);
        mMeetingRepository.addMeeting(m1);
        mMeetingRepository.addMeeting(m2);
        mMeetingRepository.addMeeting(m3);

        List<Meeting> meetingList = mMeetingRepository.sortByPlace(placeFilter);

        assertTrue(meetingList.size() == 2);
        for (Meeting m: meetingList) {
            assertEquals(m.getRoom().toString(),placeFilter);
        }
    }

    /**
     * Filter the list by Date, and return array of meetings.
     * then check if size list is equals and the meeting have correct date.
     */
    @Test
    public void sortByDateMeeting(){
        String dateFilter = "18/11/2019";
        //month in calendar is an array start to 0: November == 10.
        mCalendar.set(2019,10,18);
        Date date = mCalendar.getTime();
        mCalendar.set(2019,11,27);
        Date date1 = mCalendar.getTime();

        Meeting m1 = new Meeting(date,ROOM_LIST.get(5),"Réunion Test", MAILS);
        Meeting m2 = new Meeting(date1,ROOM_LIST.get(8),"Réunion Test", MAILS);
        Meeting m3 = new Meeting(date,ROOM_LIST.get(2),"Réunion Test", MAILS);

        mMeetingRepository.addMeeting(m1);
        mMeetingRepository.addMeeting(m2);
        mMeetingRepository.addMeeting(m3);

        List<Meeting> meetingList = mMeetingRepository.sortByDate(dateFilter);

        assertEquals(2, meetingList.size());
        for (Meeting m:meetingList) {
            assertEquals(m.getDateFormated(), dateFilter);
        }
    }

}
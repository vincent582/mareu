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
     * Sort the list by place, and sort.
     * then check if items are at good position.
     */
    @Test
    public void sortByPlaceMeeting(){
        Meeting m1 = new Meeting(currentTime,ROOM_LIST.get(5),"Réunion Test", MAILS);
        Meeting m2 = new Meeting(currentTime,ROOM_LIST.get(8),"Réunion Test", MAILS);
        Meeting m3 = new Meeting(currentTime,ROOM_LIST.get(2),"Réunion Test", MAILS);
        mMeetingRepository.addMeeting(m1);
        mMeetingRepository.addMeeting(m2);
        mMeetingRepository.addMeeting(m3);

        mMeetingRepository.sortByPlace();

        assertEquals(mMeetingRepository.getMeetingsList().get(0).getRoom().getName(), m3.getRoom().getName());
        assertEquals(mMeetingRepository.getMeetingsList().get(1).getRoom().getName(), m1.getRoom().getName());
        assertEquals(mMeetingRepository.getMeetingsList().get(2).getRoom().getName(), m2.getRoom().getName());
    }

    /**
     * Copy the list of meeting inside new one.
     * Sort the list by Date, and sort the new one by comparator.
     * then check if lists are equals and the item at position too.
     */
    @Test
    public void sortByDateMeeting(){
        mCalendar.set(2019,11,18);
        Date date = mCalendar.getTime();
        mCalendar.set(2019,11,27);
        Date date1 = mCalendar.getTime();
        mCalendar.set(2019,11,15);
        Date date2 = mCalendar.getTime();

        Meeting m1 = new Meeting(date,ROOM_LIST.get(5),"Réunion Test", MAILS);
        Meeting m2 = new Meeting(date1,ROOM_LIST.get(8),"Réunion Test", MAILS);
        Meeting m3 = new Meeting(date2,ROOM_LIST.get(2),"Réunion Test", MAILS);

        mMeetingRepository.addMeeting(m1);
        mMeetingRepository.addMeeting(m2);
        mMeetingRepository.addMeeting(m3);

        mMeetingRepository.sortByDate();

        assertEquals(mMeetingRepository.getMeetingsList().get(0).getDate(), m3.getDate());
        assertEquals(mMeetingRepository.getMeetingsList().get(1).getDate(), m1.getDate());
        assertEquals(mMeetingRepository.getMeetingsList().get(2).getDate(), m2.getDate());
    }

}
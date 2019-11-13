package com.pdv.mareu;

import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Repository.MeetingRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.pdv.mareu.ApiService.MeetingGeneratorApi.FAKE_MEETING;
import static com.pdv.mareu.ApiService.MeetingGeneratorApi.MAILS;
import static com.pdv.mareu.ApiService.MeetingGeneratorApi.ROOM_LIST;
import static org.junit.Assert.*;

public class MainActivityUnitTest {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetingList;
    private Date currentTime = Calendar.getInstance().getTime();

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
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(4),"Réunion Test", MAILS,"09H25");
        mMeetingRepository.addMeeting(mMeeting);
        assertTrue(mMeetingList.contains(mMeeting));
    }

    /**
     * Create and Add a meeting, check if List of meeting contains it.
     * Then delete the meeting and check than is not contains anymore.
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(4),"Réunion Test", MAILS,"09H25");
        mMeetingRepository.addMeeting(mMeeting);
        assertTrue(mMeetingList.contains(mMeeting));
        mMeetingRepository.deleteMeeting(mMeeting);
        assertFalse(mMeetingList.contains(mMeeting));
    }


    /**
     * Copy the list of meeting inside new one.
     * Sort the list by place, and sort the new one by comparator.
     * then check if lists are equals and the item at position too.
     */
    @Test
    public void sortByPlaceMeeting(){
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(4),"Réunion Test", MAILS,"09H25");
        Meeting mMeeting1 = new Meeting(currentTime,ROOM_LIST.get(5),"Réunion Test", MAILS,"19H30");

        mMeetingRepository.addMeeting(mMeeting);
        mMeetingRepository.addMeeting(mMeeting1);

        mMeetingRepository.sortByPlace();

        assertEquals(mMeetingRepository.getMeetingsList().get(0).getRoom().getName(),mMeeting.getRoom().getName());
        assertEquals(mMeetingRepository.getMeetingsList().get(1).getRoom().getName(),mMeeting1.getRoom().getName());
    }

    /**
     * Copy the list of meeting inside new one.
     * Sort the list by Date, and sort the new one by comparator.
     * then check if lists are equals and the item at position too.
     */
    @Test
    public void sortByDateMeeting(){
        Meeting mMeeting = new Meeting(currentTime,ROOM_LIST.get(4),"Réunion Test", MAILS,"09H25");
        Meeting mMeeting1 = new Meeting(currentTime,ROOM_LIST.get(4),"Réunion Test", MAILS,"09H25");
        mMeetingRepository.addMeeting(mMeeting);
        mMeetingRepository.addMeeting(mMeeting1);
        List<Meeting> listToSort = mMeetingList;
        mMeetingRepository.sortByDate();
        listToSort.sort(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                return m1.getDate().compareTo(m2.getDate());
            }
        });
        assertTrue(mMeetingList.equals(listToSort));
        assertEquals(mMeetingList.get(1).getDate(),listToSort.get(1).getDate());
    }

}
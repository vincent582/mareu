package com.pdv.mareu.DI;


import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.Repository.MeetingRepository;

public class DI {

    public static MeetingRepository createMeetingRepository() {
        return new MeetingRepository(new MeetingApiService());
    }

}

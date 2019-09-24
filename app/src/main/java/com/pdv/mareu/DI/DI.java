package com.pdv.mareu.DI;


import com.pdv.mareu.Service.MeetingApiService;

public class DI {

    private static MeetingApiService sService = new MeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return sService;
    }

}

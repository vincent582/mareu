package com.pdv.mareu.Base;

import android.support.v7.app.AppCompatActivity;

import com.pdv.mareu.DI.DI;
import com.pdv.mareu.Repository.MeetingRepository;

public class BaseApplication extends AppCompatActivity {

    protected MeetingRepository mMeetingRepository = DI.createMeetingRepository();

    public MeetingRepository getMeetingRepository(){
        return mMeetingRepository;
    }

}

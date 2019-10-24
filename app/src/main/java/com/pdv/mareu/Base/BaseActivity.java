package com.pdv.mareu.Base;

import android.support.v7.app.AppCompatActivity;
import com.pdv.mareu.MareuApplication;
import com.pdv.mareu.Repository.MeetingRepository;

public abstract class BaseActivity extends AppCompatActivity {

    public MeetingRepository getMeetingRepository(){
        return ((MareuApplication) getApplication()).getMeetingRepository();
    }
}

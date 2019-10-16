package com.pdv.mareu.Ui.CreateMeetingActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdv.mareu.Base.BaseApplication;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Ui.MainActivity.MeetingFragment;

public class CreateMeetingActivity extends BaseApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        CreateMeetingFragment fragment = new CreateMeetingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.create_activity_main_layout, fragment)
                .commit();
    }
}

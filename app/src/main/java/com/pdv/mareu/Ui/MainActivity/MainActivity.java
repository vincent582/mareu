package com.pdv.mareu.Ui.MainActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pdv.mareu.Base.BaseActivity;
import com.pdv.mareu.R;
import com.pdv.mareu.Ui.CreateMeetingActivity.CreateMeetingActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureFragment();
        configureToolbar();
        configureFABAddMeeting();
    }

    private void configureFragment() {
        MeetingFragment fragment = new MeetingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main, fragment)
                .commit();
    }

    private void configureToolbar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureFABAddMeeting() {
        FloatingActionButton add_meeting_fab = findViewById(R.id.add_meeting_fab);
        add_meeting_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
}

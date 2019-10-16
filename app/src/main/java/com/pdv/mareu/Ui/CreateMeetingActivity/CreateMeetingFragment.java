package com.pdv.mareu.Ui.CreateMeetingActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pdv.mareu.ApiService.MeetingGeneratorApi;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMeetingFragment extends Fragment {

    @BindView(R.id.create_meeting_subject_et) TextView mMeetingSubject;
    @BindView(R.id.time_dp) TimePicker mMeetingTime;
    @BindView(R.id.roomSpinner_sp) Spinner mMeetingRoom;
    @BindView(R.id.contributor_1_et) TextView mMeetingContributor1;
    @BindView(R.id.contributor_2_et) TextView mMeetingContributor2;
    @BindView(R.id.contributor_3_et) TextView mMeetingContributor3;
    @BindView(R.id.create_meeting_btn) Button mCreateMeetingBtn;

    public MeetingRepository mMeetingRepository;

    public CreateMeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_meeting, container, false);
        ButterKnife.bind(this,view);

        configureRoomSpinner();

        mMeetingRepository = ((CreateMeetingActivity)getActivity()).getMeetingRepository();
        configureButtonCreateMeeting();

        return view;
    }

    private void configureRoomSpinner() {
        List<String> room = MeetingGeneratorApi.MAILS;
        //add values in area arrayList
        mMeetingRoom.setAdapter(new ArrayAdapter<String>(getContext()
                , android.R.layout.simple_spinner_dropdown_item, room));
    }


    private void configureButtonCreateMeeting() {
        mCreateMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Subject of Meeting
                String subjet = mMeetingSubject.getText().toString();

                //get all mail contributor and create ArrayList
                String mail1 = mMeetingContributor1.getText().toString();
                String mail2 = mMeetingContributor2.getText().toString();
                String mail3 = mMeetingContributor3.getText().toString();
                List<String> mails = new ArrayList<String>();
                mails.add(mail1);
                mails.add(mail2);
                mails.add(mail3);

                //get Time
                String hour = mMeetingTime.getCurrentHour().toString();
                String minute = mMeetingTime.getCurrentMinute().toString();
                String time = hour + "h" + minute;

                //create new meeting
                Meeting meeting = new Meeting("salle 2",subjet,mails,time);
                mMeetingRepository.addMeeting(meeting);
                getActivity().finish();
            }
        });
    }

}

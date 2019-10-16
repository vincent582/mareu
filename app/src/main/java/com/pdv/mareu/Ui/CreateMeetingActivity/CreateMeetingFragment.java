package com.pdv.mareu.Ui.CreateMeetingActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;
import com.pdv.mareu.Ui.MainActivity.MainActivity;

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

        mMeetingRepository = ((CreateMeetingActivity)getActivity()).getMeetingRepository();

        configureButtonCreateMeeting();

        return view;
    }

    private void configureButtonCreateMeeting() {
        mCreateMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Subject of Meeting
                String subjet = mMeetingSubject.getText().toString();
                Log.i("TAG", "Subjet: "+subjet);

                //get all mail contributor and create ArrayList
                String mail1 = mMeetingContributor1.getText().toString();
                String mail2 = mMeetingContributor1.getText().toString();
                String mail3 = mMeetingContributor1.getText().toString();
                List<String> mails = new ArrayList<>();
                mails.add(mail1);
                mails.add(mail2);
                mails.add(mail3);

                Meeting meeting = new Meeting("room",subjet,mails);
                mMeetingRepository.addMeeting(meeting);
                getActivity().finish();
            }
        });
    }

}

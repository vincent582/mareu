package com.pdv.mareu.Ui.CreateMeetingActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pdv.mareu.ApiService.MeetingGeneratorApi;
import com.pdv.mareu.Base.BaseActivity;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.Model.Room;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;
import com.pdv.mareu.Ui.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pdv.mareu.Utils.Utils.isEmailValid;

public class CreateMeetingFragment extends Fragment implements View.OnClickListener, DialogTimePikerFragment.DialogTimePickerListener, DialogContributorSelectorFragment.DialogContributorListener, DialogDatePickerFragment.DialogDatePickerListener {

    @BindView(R.id.create_meeting_subject_et) TextView mMeetingSubject;
    @BindView(R.id.time_selector_btn) Button mMeetingSelectTime;
    @BindView(R.id.roomSpinner_sp) Spinner mMeetingRoom;
    @BindView(R.id.contributor_selector_btn) Button mContributorSelector;
    @BindView(R.id.create_meeting_btn) Button mCreateMeetingBtn;
    @BindView(R.id.cancel_create_btn) Button mCancelBtn;
    @BindView(R.id.list_contributor_tv) TextView mContributorList;
    @BindView(R.id.date_selector_btn) Button mDateSelectorBtn;

    public MeetingRepository mMeetingRepository;
    private String subjet;
    private String timeFormated;
    private String dateFormated;
    private Room selectedRoom;
    private List<String> mails = new ArrayList<String>();

    private final int TAG_BUTTON_CONTRIBUTOR = 0;
    private final int TAG_BUTTON_TIME = 1;
    private final int TAG_BUTTON_CANCEL = 2;
    private final int TAG_BUTTON_CREATE = 3;
    private final int TAG_BUTTON_DATE = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_meeting, container, false);
        ButterKnife.bind(this,view);
        mMeetingRepository = ((BaseActivity)getActivity()).getMeetingRepository();
        configureClickListener();
        configureRoomSpinner();
        return view;
    }

    private void configureClickListener() {
        mContributorSelector.setOnClickListener(this);
        mContributorSelector.setTag(TAG_BUTTON_CONTRIBUTOR);
        mMeetingSelectTime.setOnClickListener(this);
        mMeetingSelectTime.setTag(TAG_BUTTON_TIME);
        mCancelBtn.setOnClickListener(this);
        mCancelBtn.setTag(TAG_BUTTON_CANCEL);
        mCreateMeetingBtn.setOnClickListener(this);
        mCreateMeetingBtn.setTag(TAG_BUTTON_CREATE);
        mDateSelectorBtn.setOnClickListener(this);
        mDateSelectorBtn.setTag(TAG_BUTTON_DATE);
    }

    @Override
    public void onClick(View v) {
        switch ((int) v.getTag()) {
            case TAG_BUTTON_CANCEL:
                getActivity().finish();
                break;
            case TAG_BUTTON_TIME:
                showTimePikerDialog();
                break;
            case TAG_BUTTON_CONTRIBUTOR:
                showContributorDialog();
                break;
            case TAG_BUTTON_CREATE:
                createMeeting();
                break;
            case TAG_BUTTON_DATE:
                showDatePikerDialog();
                break;
            default:
        }
    }

    private void showDatePikerDialog() {
        DialogDatePickerFragment dialog = new DialogDatePickerFragment();
        dialog.setTargetFragment(CreateMeetingFragment.this,3);
        dialog.show(getFragmentManager(),"DialogDatePickerFragment");
    }

    private void showTimePikerDialog() {
        DialogTimePikerFragment dialog = new DialogTimePikerFragment();
        dialog.setTargetFragment(CreateMeetingFragment.this,1);
        dialog.show(getFragmentManager(), "DialogTimePikerFragment");
    }

    public void showContributorDialog() {
        DialogContributorSelectorFragment dialog = new DialogContributorSelectorFragment();
        dialog.setTargetFragment(CreateMeetingFragment.this,2);
        dialog.show(getFragmentManager(), "DialogContributorSelectorFragment");
    }

    private void configureRoomSpinner() {
        //get List Room
        List<Room> rooms = mMeetingRepository.getMeetingsRoomsList();
        //add values in room arrayList
        mMeetingRoom.setAdapter(new ArrayAdapter<Room>(getContext()
                ,android.R.layout.simple_spinner_dropdown_item, rooms));
        mMeetingRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoom = (Room) mMeetingRoom.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void createMeeting() {
        subjet = mMeetingSubject.getText().toString();
        if (subjet.isEmpty()){
            Toast.makeText(getContext(),R.string.infoAddSubjectToMeeting, Toast.LENGTH_SHORT).show();
        }
        else if((timeFormated == null) || (dateFormated == null)){
            Toast.makeText(getContext(),R.string.infoAddDateTimeToMeeting, Toast.LENGTH_SHORT).show();
        }
        else{
            Meeting meeting = new Meeting(dateFormated,selectedRoom,subjet,mails,timeFormated);
            mMeetingRepository.addMeeting(meeting);
            getActivity().finish();
        }
    }

    public void showListContributor(){
        mContributorList.setVisibility(View.VISIBLE);
        String textmails = "";
        for (String mail: mails){ textmails += mail+"\n";}
        mContributorList.setText(textmails);
    }

    @Override
    public void onDialogContributorValidateClick(DialogFragment dialog) {
        EditText contributor = dialog.getDialog().findViewById(R.id.contributor_edit_txt);
        CheckBox addContributor = dialog.getDialog().findViewById(R.id.add_contributor_checkbox);

        if (isEmailValid(contributor.getText().toString())){
            mails.add(contributor.getText().toString());
            Toast.makeText(this.getContext(),R.string.contributorAddWithSuccess,Toast.LENGTH_SHORT).show();
            if (addContributor.isChecked()){
                showContributorDialog();
            }else {
                showListContributor();
            }
        }
        else {
            Toast.makeText(this.getContext(),R.string.mailNotValide,Toast.LENGTH_SHORT).show();
            showContributorDialog();
        }
    }

    @Override
    public void onDialogCancelContributor(DialogFragment dialog) {
        showListContributor();
        dialog.dismiss();
    }

    @Override
    public void onDialogTimePikerValidateClick(DialogFragment dialog) {
        TimePicker time = (TimePicker) dialog.getDialog().findViewById(R.id.time_dp);
        int hour = time.getCurrentHour();
        int minute = time.getCurrentMinute();
        timeFormated = String.format("%02dh%02d", hour, minute);
        mMeetingSelectTime.setText(timeFormated);
        Toast.makeText(this.getContext(),R.string.choiceConfirmed,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogDatePikerValidateClick(DialogDatePickerFragment dialog) {
        DatePicker date = (DatePicker) dialog.getDialog().findViewById(R.id.date_dp);
        int day = date.getDayOfMonth();
        int month = date.getMonth();
        int year = date.getYear();
        dateFormated = String.format("%d-%02d-%02d",year,month,day);
        mDateSelectorBtn.setText(dateFormated);
    }
}
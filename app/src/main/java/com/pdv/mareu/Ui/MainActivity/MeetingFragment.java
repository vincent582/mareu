package com.pdv.mareu.Ui.MainActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.pdv.mareu.Base.BaseActivity;
import com.pdv.mareu.Model.Room;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;
import com.pdv.mareu.Utils.Dialog.DialogDatePickerFragment;
import com.pdv.mareu.Utils.Dialog.DialogPlaceSpinner;
import com.pdv.mareu.Utils.Event.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment implements DialogDatePickerFragment.DialogDatePickerListener, DialogPlaceSpinner.DialogPlaceSpinnerListener {

    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;
    private MeetingItemRecyclerViewAdapter adapter;
    private FloatingActionButton restoreDataFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingRepository = ((BaseActivity) getActivity()).getMeetingRepository();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.meeting_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        configureFABRestoreData();
        initList();
        return view;

    }

    private void configureFABRestoreData() {
        restoreDataFab = getActivity().findViewById(R.id.back_filter_meeting_fab);
        restoreDataFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initList();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.filter_by_date_item:
                showDatePikerDialog();
                return true;
            case R.id.filter_by_place_item:
                showSpinnerPlaceDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("RestrictedApi")
    private void initList() {
        adapter = new MeetingItemRecyclerViewAdapter(mMeetingRepository.getMeetingsList());
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void filterListByDate(String dateString){
        adapter.updateList(mMeetingRepository.filterByDate(dateString));
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.VISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void filterListByPlace(String place){
        adapter.updateList(mMeetingRepository.filterByPlace(place));
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mMeetingRepository.deleteMeeting(event.mMeeting);
        initList();
    }

    private void showDatePikerDialog() {
        DialogDatePickerFragment dialog = new DialogDatePickerFragment();
        dialog.setTargetFragment(MeetingFragment.this,5);
        dialog.show(getFragmentManager(),"DialogDatePickerFragment");
    }

    private void showSpinnerPlaceDialog() {
        DialogPlaceSpinner dialog = new DialogPlaceSpinner();
        dialog.setTargetFragment(MeetingFragment.this,6);
        dialog.show(getFragmentManager(),"DialogSpinnerPlaceFragment");
    }

    @Override
    public void onDialogDatePikerValidateClick(DialogDatePickerFragment dialogDatePickerFragment) {
        DatePicker datePicker = (DatePicker) dialogDatePickerFragment.getDialog().findViewById(R.id.date_dp);
        int mDay = datePicker.getDayOfMonth();
        int mMonth = datePicker.getMonth();
        int mYear = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear,mMonth,mDay);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        filterListByDate(dateString);
    }

    @Override
    public void onDialogPlaceSpinnerValidateClick(DialogFragment dialog) {
        Spinner spinner = (Spinner) dialog.getDialog().findViewById(R.id.dialog_room_spinner_sp);
        Room room = (Room) spinner.getSelectedItem();
        filterListByPlace(room.toString());
    }
}



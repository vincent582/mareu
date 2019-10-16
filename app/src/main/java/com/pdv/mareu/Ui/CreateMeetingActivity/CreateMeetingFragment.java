package com.pdv.mareu.Ui.CreateMeetingActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdv.mareu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMeetingFragment extends Fragment {


    public CreateMeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_meeting, container, false);
        return view;
    }

}

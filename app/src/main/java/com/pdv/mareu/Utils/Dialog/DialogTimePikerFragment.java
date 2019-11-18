package com.pdv.mareu.Utils.Dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.pdv.mareu.R;

public class DialogTimePikerFragment extends DialogFragment {

    public interface DialogTimePickerListener{
        public void onDialogTimePikerValidateClick(DialogFragment dialog);
    }

    DialogTimePickerListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_time_picker,null))
            .setMessage("Heure de réunion")
            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mListener.onDialogTimePikerValidateClick(DialogTimePikerFragment.this);
                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DialogTimePikerFragment.this.getDialog().cancel();
                }
            });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (DialogTimePickerListener) getTargetFragment();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+" must implement DialogTimePickerListener");
        }
    }
}

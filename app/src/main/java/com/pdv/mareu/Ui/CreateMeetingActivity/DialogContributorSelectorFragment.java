package com.pdv.mareu.Ui.CreateMeetingActivity;

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

public class DialogContributorSelectorFragment extends DialogFragment {

    public interface DialogContributorListener{
        public void onDialogContributorValidateClick(DialogFragment dialog);
        public void onDialogAddContributorClick(DialogFragment dialog);
        public void onDialogCancelContributor(DialogFragment dialog);
    }

    DialogContributorListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(R.layout.dialog_contributor)
        .setTitle("Ajouter des participants")
        .setMessage("Entrez une adresse Email et cliquez sur \"ajouter\" !")
        .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogCancelContributor(DialogContributorSelectorFragment.this);
            }
        })
        .setNeutralButton("Ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogAddContributorClick(DialogContributorSelectorFragment.this);
            }
        })
        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogContributorValidateClick(DialogContributorSelectorFragment.this);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (DialogContributorListener) getTargetFragment();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+" must implement DialogContributorListener");
        }
    }
}

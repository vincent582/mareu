package com.pdv.mareu.Ui.MainActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdv.mareu.Event.DeleteMeetingEvent;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView pastil;
    private TextView title;
    private TextView mails;
    private ImageView delete;

    public MeetingItemViewHolder(@NonNull View itemView) {
        super(itemView);
        pastil = itemView.findViewById(R.id.item_list_pastil);
        title = itemView.findViewById(R.id.item_list_title);
        mails = itemView.findViewById(R.id.item_mail_list);
        delete = itemView.findViewById(R.id.delete_item_iv);
    }

    public void updateWithMeeting(Meeting meeting){
        this.title.setText(meeting.getSubject()+" - "+meeting.getRoom() +" - "+meeting.getTime());

        List<String> mails = meeting.getMailContributor();
        String textmails = "";
        for (String mail: mails){
            textmails += mail+" , ";
        }
        this.mails.setText(textmails);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

}

package com.pdv.mareu.Ui.MainActivity;

import android.graphics.Color;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_title) TextView title;
    @BindView(R.id.item_list_pastil) ImageView pastil;
    @BindView(R.id.item_mail_list) TextView mails;
    @BindView(R.id.delete_item_iv) ImageView delete;

    private String textmails = "";

    public MeetingItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void updateWithMeeting(Meeting meeting){
        this.pastil.getDrawable().setTint(Color.parseColor(meeting.getRoom().getColor()));
        this.title.setText(meeting.getSubject()+" - "+meeting.getRoom() +" - "+meeting.getTimeFormated() + "\n"+meeting.getDateFormated());

        List<String> mails = meeting.getMailContributor();
        for (int i = 0; i < mails.size(); i++) {
            textmails += mails.get(i);
            if (i == mails.size()-1){
                textmails += ".";
            }else {
                textmails += " , ";
            }
        }
        this.mails.setText(textmails);

        this.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

}

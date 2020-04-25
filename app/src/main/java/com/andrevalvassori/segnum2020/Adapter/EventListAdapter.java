package com.andrevalvassori.segnum2020.Adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrevalvassori.segnum2020.Controller.AlertDetailActivity;
import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

import java.util.List;
import java.util.Random;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder>{

    private List<EventDTO> events = DataStore.sharedInstance().getEvents();
    private View view;
    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_events, parent, false);
        this.view = view;

        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {

        EventDTO event = events.get(position);

        holder.txtEventName.setText(event.getName());
        holder.txtEventCategory.setText(event.getEventTypeDTO().getName());
        holder.txtEventCreator.setText(event.getUser().getName());
        holder.txtCreatedAt.setText(event.getCreated_at());

//        Random r = new Random();
//        int randomInt = r.nextInt(24) + 1;
//        String uri = "@drawable/list_bg_" + randomInt;
//
//        int imageResource = view.getResources().getIdentifier(uri, null, view.getContext().getPackageName());
//        Drawable res = view.getResources().getDrawable(imageResource);
//        holder.imgEventPicture.setImageDrawable(res);
        holder.imgEventPicture.setImageDrawable(null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataStore.sharedInstance().getContext(), AlertDetailActivity.class);
                intent.putExtra("position", String.valueOf(position));
                DataStore.sharedInstance().getContext().startActivity(intent);
                Log.d("TAG",String.valueOf(position));
            }
        });
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        TextView txtEventName;
        TextView txtEventCategory;
        TextView txtEventCreator;
        TextView txtCreatedAt;
        ImageView imgEventPicture;

        public  EventHolder(@NonNull View itemView) {
            super(itemView);

            txtEventName = itemView.findViewById(R.id.txtEventName_List);
            txtEventCategory = itemView.findViewById(R.id.txtPrimaryCategory_List);
            txtEventCreator = itemView.findViewById(R.id.txtEventCreator);
            txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
            imgEventPicture = itemView.findViewById(R.id.imgEventPicture_List);
        }
    }


}

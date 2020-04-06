package com.andrevalvassori.segnum2020.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventHolder>{

    private List<EventDTO> events = DataStore.sharedInstance().getEvents();

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_events, parent, false);

        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {

        EventDTO event = events.get(position);

        holder.txtEventName.setText(event.getName());
        holder.txtEventCategory.setText(event.getEventTypeDTO().getName());
        holder.txtEventCreator.setText(event.getUser().getName());
        holder.txtCreatedAt.setText(event.getCreated_at());


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

        public  EventHolder(@NonNull View itemView) {
            super(itemView);

            txtEventName = itemView.findViewById(R.id.txtEventName_List);
            txtEventCategory = itemView.findViewById(R.id.txtPrimaryCategory_List);
            txtEventCreator = itemView.findViewById(R.id.txtEventCreator);
            txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
        }
    }


}

package com.andrevalvassori.segnum2020.Controller.MainFragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.Controller.MainActivity;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

public class EventsFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    View view;
    private RecyclerView eventRecycler;
    private EventListAdapter adapter;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events,container,false);

        eventRecycler = view.findViewById(R.id.listEvents_fragment);
        adapter = new EventListAdapter();
        eventRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eventRecycler.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    public RecyclerView getRecycler()
    {
        return eventRecycler;
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

}

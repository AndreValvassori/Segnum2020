package com.andrevalvassori.segnum2020.Controller.MainFragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.R;

public class EventsFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    View view;
    private RecyclerView eventRecycler;
    private EventListAdapter adapter;

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
}

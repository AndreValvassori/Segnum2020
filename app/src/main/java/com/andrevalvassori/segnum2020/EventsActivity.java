package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.Singleton.DataStorage;

public class EventsActivity extends AppCompatActivity {
    private RecyclerView eventRecycler;
    private EventListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

//        DataStorage.sharedInstance().setContext(this);

        eventRecycler = findViewById(R.id.listEvents);
        adapter = new EventListAdapter();
        eventRecycler.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lista de Alertas - Segnum");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eventRecycler.setLayoutManager(manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

package com.andrevalvassori.segnum2020.Controller.MainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.Adapter.MyEventListAdapter;
import com.andrevalvassori.segnum2020.Controller.AlertDetailActivity;
import com.andrevalvassori.segnum2020.Controller.MainActivity;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

import okhttp3.internal.Util;

public class MyEventsFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    View view;
    private RecyclerView eventRecycler;
    private MyEventListAdapter adapter;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment_events,container,false);

        eventRecycler = view.findViewById(R.id.myListEvents_fragment);
        adapter = new MyEventListAdapter();
        eventRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eventRecycler.setLayoutManager(manager);


        gestureDetector = new GestureDetector(DataStore.sharedInstance().getContext(),new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                // Toast.makeText(MainActivity.this, "onDown", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Toast.makeText(DataStore.sharedInstance().getContext(), "onDown", Toast.LENGTH_SHORT).show();
                View view = eventRecycler.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                int position = eventRecycler.getChildAdapterPosition(view);

                Intent intent = new Intent(DataStore.sharedInstance().getContext(), AlertDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}

package com.andrevalvassori.segnum2020.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TabHost;

import com.andrevalvassori.segnum2020.Adapter.SectionsPageAdapter;
import com.andrevalvassori.segnum2020.Controller.MainFragments.EventsFragment;
import com.andrevalvassori.segnum2020.Controller.MainFragments.MyEventsFragment;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabHost tabHostWindow = null;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    EventsFragment eventFragments;
    MyEventsFragment myEventFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStore.sharedInstance().setContext(this);
        DataStore.sharedInstance().loadAllEvents();

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.containerMain);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabsMain);
        tabLayout.setupWithViewPager(mViewPager);

        FragmentManager manager = getSupportFragmentManager();
        Intent intent = getIntent();

        commitEvento(manager, eventFragments);

        //DataStore.sharedInstance().LoadAndNotifyEventsa(eventFragments.getRecycler());
        //DataStore.sharedInstance().loadAllEvents();

    }

    private void commitEvento(FragmentManager manager, Fragment fragmento) {
//        FragmentTransaction tx = manager.beginTransaction();
//        Bundle parametros = new Bundle();
//        parametros.putSerializable("event", event);
//        fragmento.setArguments(parametros);
//
//        tx.commit();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        eventFragments = new EventsFragment();
        myEventFragments = new MyEventsFragment();
        adapter.addFragment(eventFragments, "Alertas");
        adapter.addFragment(myEventFragments, "Meus Alertas");
        viewPager.setAdapter(adapter);
    }

}



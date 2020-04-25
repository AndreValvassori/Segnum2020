package com.andrevalvassori.segnum2020.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.andrevalvassori.segnum2020.Adapter.SectionsPageAdapter;
import com.andrevalvassori.segnum2020.Controller.MainFragments.EventsFragment;
import com.andrevalvassori.segnum2020.Controller.MainFragments.MapFragment;
import com.andrevalvassori.segnum2020.Controller.MainFragments.MyEventsFragment;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_ITEM1 = R.id.mainMenu_Item1;

    TabHost tabHostWindow = null;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    EventsFragment eventFragments;
    MyEventsFragment myEventFragments;
    MapFragment mapFragment;


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

        //commitEvento(manager, mapFragment);

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

        mapFragment = new MapFragment();
        eventFragments = new EventsFragment();

        adapter.addFragment(eventFragments, "Alertas Próximos");
        adapter.addFragment(mapFragment, "Mapa");
       
        if (DataStore.sharedInstance().getUser() != null/* || DataStore.sharedInstance().getUser().getId() == 0*/) {
            myEventFragments = new MyEventsFragment();
            adapter.addFragment(myEventFragments, "Meus Alertas");
        }
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(DataStore.sharedInstance().getUser() != null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.mainmenu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM1:
                Intent intentPerfilActivity = new Intent(this, PerfilActivity.class);
                this.startActivity(intentPerfilActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
    if(DataStore.sharedInstance().getUser() == null && DataStore.sharedInstance().enterWithLogin)
        finish();
    super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            BackButton();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        BackButton();
    }

    private void BackButton()
    {
        super.onBackPressed();
        DataStore.sharedInstance().setUser(null);
        this.finish();
    }
}



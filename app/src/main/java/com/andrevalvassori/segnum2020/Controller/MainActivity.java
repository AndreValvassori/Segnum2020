package com.andrevalvassori.segnum2020.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.Adapter.SectionsPageAdapter;
import com.andrevalvassori.segnum2020.Controller.MainFragments.EventsFragment;
import com.andrevalvassori.segnum2020.Controller.MainFragments.MapFragment;
import com.andrevalvassori.segnum2020.Controller.MainFragments.MyEventsFragment;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.material.tabs.TabLayout;

import java.net.URL;

import me.pushy.sdk.Pushy;

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

        if (!Pushy.isRegistered(getApplicationContext())) {
            new RegisterForPushNotificationsAsync().execute();
        }

        Pushy.listen(this);

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
        myEventFragments = new MyEventsFragment();
        adapter.addFragment(eventFragments, "Alertas");
        adapter.addFragment(mapFragment, "Mapa");
        adapter.addFragment(myEventFragments, "Meus Alertas");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
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
        if(DataStore.sharedInstance().getUser() == null)
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

    private class RegisterForPushNotificationsAsync extends AsyncTask<Void, Void, Exception> {
        protected Exception doInBackground(Void... params) {
            try {
                // Assign a unique token to this device
                String deviceToken = Pushy.register(getApplicationContext());

                // Log it for debugging purposes
                Log.d("Segnum2020", "Pushy device token: " + deviceToken);

                // Send the token to your backend server via an HTTP GET request
                new URL("https://{YOUR_API_HOSTNAME}/register/device?token=" + deviceToken).openConnection();
            }
            catch (Exception exc) {
                // Return exc to onPostExecute
                return exc;
            }

            // Success
            return null;
        }

        @Override
        protected void onPostExecute(Exception exc) {
            // Failed?
            if (exc != null) {
                // Show error as toast message
                Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG).show();
                return;
            }

            // Succeeded, optionally do something to alert the user
        }
    }
}



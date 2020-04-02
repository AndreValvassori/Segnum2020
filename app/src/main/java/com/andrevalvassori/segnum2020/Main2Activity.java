package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.andrevalvassori.segnum2020.Singleton.DataStorage;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Timer refreshTimer;

    Button btnAlertas;
    Button btnMeusAlertas;
    Button btnEnviarAlertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DataStorage.sharedInstance().setContext(this);

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
        DataStorage.sharedInstance().SetUser(null);
        refreshTimer.cancel();
        refreshTimer.purge();
        this.finish();
    }

    private void RefreshEvents()
    {
        Log.d("Main2Activity","Trying to Load Events!");
        if(DataStorage.sharedInstance().eventChange)
        {
            Log.d("","Changes Found! Updating Events!");
            //----
            if(mMap == null || DataStorage.sharedInstance().currentEvents == null) {
                DataStorage.sharedInstance().loadAllEvents();
                return;
            }

            mMap.clear();
            DataStorage.sharedInstance().currentEvents.forEach(eventDTO ->
            {
                LatLng position =new LatLng(
                        Double.valueOf(eventDTO.getLocationDTO().getLx()),
                        Double.valueOf(eventDTO.getLocationDTO().getLy()));
                mMap.addMarker(
                        new MarkerOptions().position(position
                        ).title(eventDTO.getName())
                );
                //mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                Log.d("Main2Activity","Event: "+eventDTO.toString());
            });
            //----
            DataStorage.sharedInstance().eventChange = false;
        }
        DataStorage.sharedInstance().loadAllEvents();
    }

    private void StartEventTimer() {
        Log.d("Main2Acitivy","StartEventTimer");
        RefreshEvents();
        refreshTimer = new Timer(true);
        refreshTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RefreshEvents();
                    }
                });
            }
        },
        0,
        10000
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        RefreshEvents();
        StartEventTimer();


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public void btnSendAlertOnClick(View view)
    {
        Intent intentSendAlertActivity = new Intent(this, SendAlert.class);
        this.startActivity(intentSendAlertActivity);
    }
}

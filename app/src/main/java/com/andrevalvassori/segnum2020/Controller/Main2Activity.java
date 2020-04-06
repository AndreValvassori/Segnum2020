package com.andrevalvassori.segnum2020.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
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

    private static final int MENU_ITEM1 = R.id.mainMenu_Item1;


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

        DataStore.sharedInstance().setContext(this);

        btnAlertas = findViewById(R.id.btn_main_Alertas);

        btnAlertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerfilActivity = new Intent(Main2Activity.this, EventsActivity.class);
                startActivity(intentPerfilActivity);
            }
        });

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
        refreshTimer.cancel();
        refreshTimer.purge();
        this.finish();
    }

    private void RefreshEvents()
    {
        Log.d("Main2Activity","Trying to Load Events!");
        if(DataStore.sharedInstance().eventChange)
        {
            Log.d("","Changes Found! Updating Events!");
            //----
            if(mMap == null || DataStore.sharedInstance().currentEvents == null) {
                DataStore.sharedInstance().loadAllEvents();
                return;
            }

            mMap.clear();

            int i = 0;
            for (EventDTO evento: DataStore.sharedInstance().currentEvents) {


                LatLng position =new LatLng(
                        Double.parseDouble(evento.getLocationDTO().getLx()),
                        Double.parseDouble(evento.getLocationDTO().getLy()));
                mMap.addMarker(
                        new MarkerOptions().position(position
                        ).title(evento.getName())
                );

                if(i++ == DataStore.sharedInstance().currentEvents.size() - 1){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                }
                Log.d("Main2Activity","Event: "+evento.toString());
            }
/*
            DataStorage.sharedInstance().currentEvents.forEach(eventDTO ->
            {
                LatLng position =new LatLng(
                        Double.parseDouble(eventDTO.getLocationDTO().getLx()),
                        Double.parseDouble(eventDTO.getLocationDTO().getLy()));
                mMap.addMarker(
                        new MarkerOptions().position(position
                        ).title(eventDTO.getName())
                );
                //mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                Log.d("Main2Activity","Event: "+eventDTO.toString());
            });*/
            //----
            DataStore.sharedInstance().eventChange = false;
        }
        DataStore.sharedInstance().loadAllEvents();
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        RefreshEvents();
        StartEventTimer();
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

    public void btnSendAlertOnClick(View view)
    {
        Intent intentSendAlertActivity = new Intent(this, SendAlertActivity.class);
        this.startActivity(intentSendAlertActivity);
    }
}

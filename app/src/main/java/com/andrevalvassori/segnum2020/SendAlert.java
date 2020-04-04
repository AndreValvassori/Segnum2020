package com.andrevalvassori.segnum2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.DTO.event.EventNewSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.Model.EventType;
import com.andrevalvassori.segnum2020.Singleton.DataStorage;
import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SendAlert extends AppCompatActivity {

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    EditText etNome;
    EditText etDescricao;

    Spinner spinTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);

        etNome = findViewById(R.id.et_sendalert_Nome);
        etDescricao = findViewById(R.id.et_sendalert_Descricao);
        spinTypes = findViewById(R.id.sp_sendalert_tipo);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        DataStorage.sharedInstance().setContext(this);

        ArrayAdapter<EventType> typesAdapter = new ArrayAdapter<EventType>(this,
                android.R.layout.simple_spinner_item, DataStorage.sharedInstance().eventTypes);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypes.setAdapter(typesAdapter);
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
            }
        }
    }

    private LatLng getLastLocation(){
        final Double[] _lat = new Double[1];
        final Double[] _long = new Double[1];
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {

                                } else {
                                    _lat[0] = location.getLatitude();
                                    _long[0] = location.getLongitude();
                                    LatLng latLngLocal= new LatLng(location.getLatitude(),location.getLongitude());
                                    SendAlert(latLngLocal);
                                    /*Toast.makeText(DataStorage.sharedInstance().getContext(), "Latitude: "+_lat[0]  + " Longitude: "+_long[0]+ " EventType: "+
                                                    ((EventType) spinTypes.getSelectedItem()).getId(), Toast.LENGTH_LONG).show();*/
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
        if(_lat[0] != null && _long[0] != null)
            return new LatLng(_lat[0],_long[0]);
        else
            return null;
    }

    public void SendAlert(LatLng local)
    {
        EventNewSimplifyDTO newEvent;
        UserDTO currentUser = DataStorage.sharedInstance().getUser();
        EventType currentEventType = ((EventType) spinTypes.getSelectedItem());

        newEvent = new EventNewSimplifyDTO();
        newEvent.setName(etNome.getText().toString());
        newEvent.setDescription(etDescricao.getText().toString());
        newEvent.setUserId(currentUser.getId());
        newEvent.setEventTypeId(currentEventType.getId());

        DataStorage.sharedInstance().sendEvent(newEvent, local);
    }

    public void btnSendAlertOnClick(View view)
    {
        getLastLocation();
    }
}

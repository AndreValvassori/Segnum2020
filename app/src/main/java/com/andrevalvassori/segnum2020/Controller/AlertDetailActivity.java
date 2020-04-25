package com.andrevalvassori.segnum2020.Controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.Model.Event;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class AlertDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;
    private EditText etCategoria;
    private EditText etDescricao;
    private EventDTO event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);
        Intent intent = getIntent();

        Integer position;
        position = Integer.parseInt( (String) intent.getSerializableExtra("position"));
        event = DataStore.sharedInstance().getCurrentEvent(position);

        etCategoria = findViewById(R.id.et_alertdetail_categoria);
        etDescricao = findViewById(R.id.et_alertdetail_descricao);


        etCategoria.setText(event.getEventTypeDTO().getName());
        etDescricao.setText(event.getDescription());

        mapView = findViewById(R.id.map_alertdetail_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(event.getName() + " - Segnum");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(15);
        gmap.setMaxZoomPreference(15);
        LatLng position =new LatLng(
                Double.parseDouble(event.getLocationDTO().getLy()),
                Double.parseDouble(event.getLocationDTO().getLx()));

        gmap.moveCamera(CameraUpdateFactory.newLatLng(position));
        /*gmap.addMarker(
                new MarkerOptions().position(position
                ).title(event.getName()).snippet(event.getDescription())
        );*/

        MarkerOptions marker = new MarkerOptions().position(position).title(event.getName()).snippet(event.getDescription());

        if(event.getEventTypeDTO().getName().equals("Roubo "))
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.gun_48px));
        else if (event.getEventTypeDTO().getName().equals("Assassinato "))
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.poison_48px));
        else if (event.getEventTypeDTO().getName().equals("Furto ") )
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mask_48px));

        //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon)));

        gmap.addMarker(marker);

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e("AlertDetailAcitivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("AlertDetailAcitivity", "Can't find style. Error: ", e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

package com.andrevalvassori.segnum2020.Controller.MainFragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.Controller.SendAlertActivity;
import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.*;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> heatMapMarkers = new ArrayList<LatLng>();
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;

    private  SupportMapFragment mapFragment;

    View v;


//    public void onCreate(Bundle savedInstanceState) {
//        getMapAsync(this);
//        super.onCreate(savedInstanceState);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        v=inflater.inflate(R.layout.fragment_map, null);
//        getMapAsync(this);
//        return v;
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.mapnew);
        mapFragment.getMapAsync(this);

        Button button = (Button) rootView.findViewById(R.id.btn_map_SendAlert);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText (getContext(), "OnClick button fragment", Toast.LENGTH_LONG).show();
                Intent intentSendAlertActivity = new Intent(getContext(), SendAlertActivity.class);
                getContext().startActivity(intentSendAlertActivity);
            }
        });

        return rootView;
}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMaxZoomPreference(19f);
        mMap.setMinZoomPreference(15f);

        insertAllMarks();
    }

    public void insertAllMarks()
    {
        mMap.clear();
        heatMapMarkers.clear();

        Log.d("MapFragment","Inserting all Marks!");
        int i = 0;
        for (EventDTO evento: DataStore.sharedInstance().currentEvents) {
            LatLng position =new LatLng(
                    Double.parseDouble(evento.getLocationDTO().getLy()),
                    Double.parseDouble(evento.getLocationDTO().getLx()));
            heatMapMarkers.add(position);

            mMap.addMarker(
                    new MarkerOptions().position(position
                    ).title(evento.getName()).snippet(evento.getDescription())
            );

            if(i++ == DataStore.sharedInstance().currentEvents.size() - 1){
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }
            Log.d("MapFragment","Event: "+evento.toString());
        }
        insertHeatMap();
    }

    public void insertHeatMap()
    {
        int[] colors = {
                Color.rgb(255, 211, 0), // yellow
                Color.rgb(255, 0, 0)    // red
        };

        float[] startPoints = {
                0.2f, 1f
        };
        Gradient gradient = new Gradient(colors, startPoints);

        mProvider = new HeatmapTileProvider.Builder()
                .data(heatMapMarkers)
                .gradient(gradient)
                .build();
        mProvider.setRadius(200);

        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

        mProvider.setOpacity(0.5);
        mOverlay.clearTileCache();
    }
}

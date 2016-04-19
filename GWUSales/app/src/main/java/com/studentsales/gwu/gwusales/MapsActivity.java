package com.studentsales.gwu.gwusales;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String objectId;
    private double longitude = 0.0, latitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        Intent newIntent = getIntent();
        objectId = newIntent.getExtras().getString("location");
        final ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Items");
        parseQuery.whereEqualTo("objectId",objectId)
                .findInBackground(new FindCallback<ParseObject>() {
            public ParseGeoPoint userGeoPoint;
            public ParseObject parseObject;
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                    for (int i = 0; i<list.size(); i++){
                        parseObject = list.get(i);
                        userGeoPoint = parseObject.getParseGeoPoint("location");
                        latitude = userGeoPoint.getLatitude();
                        longitude = userGeoPoint.getLongitude();
                    }
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mapFragment.getMapAsync(MapsActivity.this);
            }
        },2000);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng userLoc = new LatLng(latitude, longitude);
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(150).strokeColor(Color.GRAY)
                .fillColor(Color.TRANSPARENT));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 16));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 16));
    }
}

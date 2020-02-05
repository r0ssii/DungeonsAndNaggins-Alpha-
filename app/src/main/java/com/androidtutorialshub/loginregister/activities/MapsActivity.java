package com.androidtutorialshub.loginregister.activities;

import android.os.Bundle;
import android.widget.Toast;


import androidx.fragment.app.FragmentActivity;

import com.androidtutorialshub.loginregister.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
        LatLng iadt = new LatLng(53.2796486, -6.1549324);
        mMap.addMarker(new MarkerOptions().position(iadt).title("Marker in iadt"));

        LatLng JohnnyFox = new LatLng(53.2217107, -6.2191994);
        mMap.addMarker(new MarkerOptions().position(JohnnyFox).title("Johnny Foxs"));

        LatLng Lighthouse = new LatLng(53.2929888, -6.1374504);
        mMap.addMarker(new MarkerOptions().position(Lighthouse).title("The Lighthouse"));

        LatLng George = new LatLng(53.343743, -6.2646836);
        mMap.addMarker(new MarkerOptions().position(George).title("The George"));

        LatLng beerkeeper = new LatLng(53.2947027, -6.1416478);
        mMap.addMarker(new MarkerOptions().position(beerkeeper).title("Beer Keeper"));

        LatLng bakers = new LatLng(53.2805924, -6.1577014);
        mMap.addMarker(new MarkerOptions().position(bakers).title("Bakers Corner"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(bakers));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }


        return false;
    }
}

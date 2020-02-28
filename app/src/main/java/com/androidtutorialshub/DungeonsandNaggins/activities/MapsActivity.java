package com.androidtutorialshub.DungeonsandNaggins.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.androidtutorialshub.DungeonsandNaggins.R;
import com.androidtutorialshub.DungeonsandNaggins.model.Pub;
import com.androidtutorialshub.DungeonsandNaggins.sql.DatabaseHelperPub;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static java.lang.Double.parseDouble;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private final FragmentActivity activity = MapsActivity.this;
    private GoogleMap mMap;
    TextView mtextView;
    TextView mGames;

    private DatabaseHelperPub databaseHelperPub;






    //The create method for the google maps activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mtextView = findViewById(R.id.textView);
        mGames = findViewById(R.id.Games);

        databaseHelperPub = new DatabaseHelperPub(activity);

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


        

        List<Pub> pubs =  databaseHelperPub.getAllPub();

        for (Pub pub : pubs) {


            //System.out.println(temp);
            //retreive all pubs from database
            // loops through each pub
            //adds a marker with the lat and long

            double la = parseDouble(pub.getLat());
            double lo = parseDouble(pub.getLng());


            LatLng position = new LatLng(la,lo );
            mMap.addMarker(new MarkerOptions().position(position).title(pub.getName()).snippet(pub.getGames()));

        }




        mMap.setOnMarkerClickListener(this);
    }




    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    @SuppressLint("ResourceType")
    @Override
    public boolean onMarkerClick(Marker marker) {



        mtextView.setText(marker.getTitle());
        mGames.setText(marker.getSnippet());

       // mDB.setText(databaseHelperPub.getAllPub());



        Toast.makeText(this,
                        " Pub Selected. ",
                Toast.LENGTH_SHORT).show();





        return false;
    }


}

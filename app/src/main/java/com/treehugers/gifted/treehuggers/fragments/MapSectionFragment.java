package com.treehugers.gifted.treehuggers.fragments;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.PictureDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.treehugers.gifted.treehuggers.R;
import com.treehugers.gifted.treehuggers.Tree;
import com.treehugers.gifted.treehuggers.Utils;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import static android.content.Context.LOCATION_SERVICE;

public class MapSectionFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    SupportMapFragment map_fragment;
    public static GoogleMap myMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient.ConnectionCallbacks connectionCallbacks;
    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener;
    public ArrayList<Tree> allTrees = new ArrayList<>();
    private double longtitude = 0;
    private double latitude = 0;
    private Bitmap bitmap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map_section, container, false);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MapSectionFragment.this.getContext());



        googleMaps(allTrees);

        Log.i("TEST", String.valueOf(checkLocationPermission()));
        if(checkLocationPermission()){
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(MapSectionFragment.this.getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            Log.i("TEST", "SUCCESSSSS");
                            // Got last known location. In some rare situations this can be null.
                            if (location != null && myMap!=null) {


                        Log.i("TEST", "CURRENT LOCATION IS: " + "Longtitude: " + location.getLongitude() + " | " + "Longtitude: " + location.getLatitude());
                                LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude()); //Store these lat lng values somewhere. These should be constant.
                                CameraUpdate zoomLocation = CameraUpdateFactory.newLatLngZoom(
                                        coordinate, 19);
                                myMap.animateCamera(zoomLocation);

                            }
                        }
                    });
        }else{

        }


        return view;
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(MapSectionFragment.this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapSectionFragment.this.getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(MapSectionFragment.this.getContext())
                        .setTitle("Location permission")
                        .setMessage("Provide your location for tree hugging")
                        .setPositiveButton("Ofcourse!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapSectionFragment.this.getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MapSectionFragment.this.getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(MapSectionFragment.this.getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        startListeningForLocation();
                    }

                } else {

                   //Denied permission
                    System.exit(0);
                    //todo: handle

                }
                return;
            }

        }
    }



    private void startListeningForLocation(){
       Log.i("TEST", "Start listening for locations");



    }

    private void googleMaps(final ArrayList<Tree> allTrees) {
        //Log.i("TEST", "LIST SIZE IS: " + allTrees.size());
        FragmentManager fm = getActivity().getSupportFragmentManager();
        CameraPosition cp = new CameraPosition.Builder()
                .target(new LatLng(54.6, 25.27)) //Just a random starting point. Will be changed in reloadData()
                .zoom(1f)
                .build();

        map_fragment = SupportMapFragment.newInstance(new GoogleMapOptions().camera(cp));
        fm.beginTransaction().replace(R.id.map, map_fragment).commit();
        map_fragment.onCreate(getArguments());
        map_fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                myMap = googleMap;

                myMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        if(MapSectionFragment.myMap != null)
                        {
                            for(int i = 0; i<allTrees.size(); i++){
                               // Log.i("TEST", String.valueOf(allTrees.get(i).latitude));
                                LatLng sydney = new LatLng(allTrees.get(i).longtitude,allTrees.get(i).latitude);
                                MapSectionFragment.myMap.addMarker(new MarkerOptions().position(sydney)
                                        .title("PAF-KIET")).setIcon(BitmapDescriptorFactory.fromResource(allTrees.get(i).hugged ? R.drawable.treehugged: R.drawable.treeunhugged));
                            }


                        }
                    }
                });




            }

        });
    }






}
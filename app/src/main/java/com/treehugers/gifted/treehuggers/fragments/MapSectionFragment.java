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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
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

    SupportMapFragment map_fragment;
    public static GoogleMap myMap;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient.ConnectionCallbacks connectionCallbacks;
    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener;
    public ArrayList<Tree> allTrees = new ArrayList<>();
    private double longtitude = 0;
    private double latitude = 0;
    private Bitmap bitmap;
    private Location mLastLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map_section, container, false);


        connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                Toast.makeText(MapSectionFragment.this.getContext(),"Connected successfuly",Toast.LENGTH_LONG).show();

                if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                    ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                            LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
                }
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        };

        connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(MapSectionFragment.this.getContext(),"Failed to connect to google maos",Toast.LENGTH_LONG).show();
            }
        };

        mGoogleApiClient = new GoogleApiClient.Builder(MapSectionFragment.this.getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                .addOnConnectionFailedListener(connectionFailedListener)
                .build();

        googleMaps(allTrees);







/*
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(MapSectionFragment.this.getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
*/
        return view;
    }





    private void googleMaps(final ArrayList<Tree> allTrees) {
        Log.i("TEST", "LIST SIZE IS: " + allTrees.size());
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
                                Log.i("TEST", String.valueOf(allTrees.get(i).latitude));
                                LatLng sydney = new LatLng(allTrees.get(i).longtitude,allTrees.get(i).latitude);
                                MapSectionFragment.myMap.addMarker(new MarkerOptions().position(sydney)
                                        .title("PAF-KIET")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.treeunhugged));
                            }


                        }
                    }
                });




            }

        });
    }






}
package com.treehugers.gifted.treehuggers.fragments;


import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.treehugers.gifted.treehuggers.R;

public class MapSectionFragment extends Fragment {

    SupportMapFragment map_fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_section, container, false);
        googleMaps();
        return view;
    }



    private void googleMaps(){

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
            public void onMapReady(GoogleMap googleMap) {
                LatLng sydney = new LatLng(-33.852, 151.211);
                googleMap.addMarker(new MarkerOptions().position(sydney)
                        .title("Marker in Sydney"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        });
    }






}
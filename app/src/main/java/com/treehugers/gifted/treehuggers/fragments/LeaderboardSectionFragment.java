package com.treehugers.gifted.treehuggers.fragments;

import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treehugers.gifted.treehuggers.R;

public class LeaderboardSectionFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard_section, container, false);
        //Initializing the mediaplayer in this method, so that we don't need to reload the sound later
        return view;
    }
}

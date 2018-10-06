package com.treehugers.gifted.treehuggers.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;

/**
 * Created by Benas on 9/7/2016.
 */
public class ViewPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public MapSectionFragment mapSectionFragment;
    public LeaderboardSectionFragment leaderFragment;
    public CouponsSectionFragment couponsFragment;


    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mapSectionFragment = new MapSectionFragment();
        leaderFragment = new LeaderboardSectionFragment();
        couponsFragment = new CouponsSectionFragment();

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return mapSectionFragment;

            case 1:
                return leaderFragment;


            case 2:
                return couponsFragment;



        }

        return null;
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
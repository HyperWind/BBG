package com.treehugers.gifted.treehuggers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.treehugers.gifted.treehuggers.fragments.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {





    private ViewPagerAdapter viewPagerAdapter;

    private AHBottomNavigation bottomNavigation;
    private ViewPager viewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Window wind = getWindow();

        //wind.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wind.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        setContentView(R.layout.activity_main);
        Utils.changeNotifBarColor("#ADC822", getWindow());



        initializeViews();
        setupBottomBar();
        //setFonts();


    }


    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }




    private void setupBottomBar(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.coupons, R.drawable.ic_wallet,R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.map, R.drawable.ic_map_marker, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.leaderboard, R.drawable.ic_leaderboard, R.color.colorAccent);

        viewpager.setOffscreenPageLimit(4);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewpager.setAdapter(viewPagerAdapter);

        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item3);


        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ADC822"));
        // bottomNavigation.setBehaviorTranslationEnabled(true);

        //bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

        bottomNavigation.setAccentColor(Color.parseColor("#f1c40f"));
        bottomNavigation.setInactiveColor(Color.parseColor("#ffffff"));


        //     bottomNavigation.setForceTint(true);
        //    bottomNavigation.setTranslucentNavigationEnabled(true);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        bottomNavigation.setColored(false);
        bottomNavigation.setCurrentItem(1);
        viewpager.setCurrentItem(1);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewpager.setCurrentItem(position);
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                viewpager.setCurrentItem(y);
            }
        });


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initializeViews(){






        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewpager = (ViewPager) findViewById(R.id.viewpager);


    }





    private void setFonts(){
        Typeface futura = Typeface.createFromAsset(getAssets(),
                "fonts/futura_light.ttf");
        Typeface corbel = Typeface.createFromAsset(getAssets(),
                "fonts/Corbel Bold.ttf");
        Typeface verdana = Typeface.createFromAsset(getAssets(),
                "fonts/Verdana.ttf");


        bottomNavigation.setTitleTypeface(verdana);
    }










    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        Runtime.getRuntime().gc();
    }
}
package com.treehugers.gifted.treehuggers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.WindowManager;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class StartingActivity extends AwesomeSplash {

    public static final long AFTER_ANIMATION_DELAY = 1000L;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        //Making app fullscreen on versions, which support it
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setAnimCircularRevealDuration(1500);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.drawable.ic_app_icon);
        configSplash.setAnimLogoSplashDuration(500);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

        configSplash.setTitleSplash("Tree Hugger");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(25f);
        configSplash.setAnimTitleDuration(500);
        configSplash.setAnimTitleTechnique(Techniques.SlideInUp);

    }

    @Override
    public void animationsFinished() {
        //Starting the MainActivity after a fixed amount of time
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartingActivity.this,LoginActivity.class));
            }
        }, StartingActivity.AFTER_ANIMATION_DELAY);

    }
}

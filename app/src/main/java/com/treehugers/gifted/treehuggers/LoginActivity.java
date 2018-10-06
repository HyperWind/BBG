package com.treehugers.gifted.treehuggers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 2;
    private TextView txt_slogan;
    private TextView txt_noAccount;
    private TextView txt_noRegistration;
    private EditText editText_username;
    private EditText editText_password;
    private Button btn_login;
    private ImageButton imgBtn_facebook;
    private ImageButton imgBtn_google;
    private ImageView circle;
    private ImageView img_login_appicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utils.changeNotifBarColor("#ADC822", getWindow());


        initializeViews();
        //setFonts();


    }

    private void initializeViews() {


        Animation left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        Animation right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        Animation bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        Animation top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fade_in_delay = AnimationUtils.loadAnimation(this, R.anim.fade_in_delay);
        Animation top_to_bottom_delay = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom_delay);


        txt_slogan = (TextView) findViewById(R.id.slogan);
        txt_noAccount = (TextView) findViewById(R.id.noAccount);
        editText_username = (EditText) findViewById(R.id.usernameEditText);
        editText_password = (EditText) findViewById(R.id.passwordEditText);
        imgBtn_facebook = (ImageButton) findViewById(R.id.login_facebook);
        imgBtn_google = (ImageButton) findViewById(R.id.login_googlePlus);
        btn_login = (Button) findViewById(R.id.buttonLogin);
        circle = (ImageView) findViewById(R.id.circleDot);
        img_login_appicon = (ImageView) findViewById(R.id.imageView);

        imgBtn_facebook.startAnimation(left_to_right);
        imgBtn_google.startAnimation(right_to_left);
        btn_login.startAnimation(bottom_to_top);
        txt_noAccount.startAnimation(top_to_bottom);
        circle.startAnimation(fade_in);
        editText_username.startAnimation(fade_in_delay);
        editText_password.startAnimation(fade_in_delay);
        txt_slogan.startAnimation(top_to_bottom_delay);



    }

    private void setFonts() {
        Typeface face_slogan = Typeface.createFromAsset(getAssets(),
                "fonts/CORBEL.TTF");
        Typeface futura = Typeface.createFromAsset(getAssets(),
                "fonts/futura_light.ttf");

        Typeface verdana = Typeface.createFromAsset(getAssets(),
                "fonts/Verdana.ttf");

        txt_slogan.setTypeface(face_slogan);
        txt_noRegistration.setTypeface(verdana);
        btn_login.setTypeface(futura);
        editText_username.setTypeface(futura);
        editText_password.setTypeface(futura);
        txt_noAccount.setTypeface(verdana);
    }

    public void login(View view) {


    }

    public void facebookLogin(View view) {

    }

    public void googleLogin(View view) {

    }


}
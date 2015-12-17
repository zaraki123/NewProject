package com.example.nelson.caliplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.nelson.caliplay.test.StartingTest;
import com.example.nelson.caliplay.user_profile.UserProfile;

public class MainActivity extends AppCompatActivity {
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userProfile(View view) {

        Intent userProfile = new Intent(this, UserProfile.class);
        if (userProfile.resolveActivity(getPackageManager()) != null) {
            startActivity(userProfile);

        }
    }

    public void test(View view) {

        Intent test = new Intent(this, StartingTest.class);
        if (test.resolveActivity(getPackageManager()) != null) {
            startActivity(test);
        }
    }



}



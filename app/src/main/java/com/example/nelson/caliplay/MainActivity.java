package com.example.nelson.caliplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nelson.caliplay.test.StartingTest;

public class MainActivity extends AppCompatActivity {

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

    public void test (View view) {

        Intent test = new Intent(this, StartingTest.class);
        if (test.resolveActivity(getPackageManager()) != null) {
            startActivity(test);
        }
    }

    public void video (View view) {

        Intent video = new Intent(this, ExerciseOverview.class);
        if (video.resolveActivity(getPackageManager()) != null) {
            startActivity(video);
        }
    }

    public void youtube (View view) {

        Intent video = new Intent(this, YouTube.class);
        if (video.resolveActivity(getPackageManager()) != null) {
            startActivity(video);
        }
    }

}


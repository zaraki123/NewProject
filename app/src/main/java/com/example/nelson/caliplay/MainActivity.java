package com.example.nelson.caliplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nelson.caliplay.test.StrengthTest;

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

        Intent test = new Intent(this, StrengthTest.class);
        if (test.resolveActivity(getPackageManager()) != null) {
            startActivity(test);
        }
    }

}


package com.example.nelson.caliplay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

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

    public void picker(View view) {

        Intent userProfile = new Intent(this, Picker.class);
        if (userProfile.resolveActivity(getPackageManager()) != null) {
            startActivity(userProfile);

        }
    }
}


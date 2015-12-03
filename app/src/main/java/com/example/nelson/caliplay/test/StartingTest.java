package com.example.nelson.caliplay.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nelson.caliplay.ExerciseProfile;
import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class StartingTest extends AppCompatActivity {

    private MyApplication app;
    private ExerciseProfile exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_test);
    }

    public void start(View view) {
        Intent startTest = new Intent(this, Timer.class);
        if (startTest.resolveActivity(getPackageManager()) != null) {
            startActivity(startTest);
        }
    }


}

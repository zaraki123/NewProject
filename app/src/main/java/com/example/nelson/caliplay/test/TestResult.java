package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Zaraki on 01/12/2015.
 */
public class TestResult extends AppCompatActivity {

    private int secs = 0, exerciseLevel = 0;
    private boolean testComplete = false;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_test);
        Bundle extras = getIntent().getExtras();
        secs = extras.getInt("result");
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");

    }

    public void result(View view) {
        switch (view.getId()) {
            case R.id.very_easy:

                if (secs >= 40) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    goBack(secs, testComplete);
                } else if (secs <= 10) {
                    secs *= 2;
                    goBack(secs, testComplete);
                } else {
                    secs *= 1.5;
                    goBack(secs, testComplete);
                }

                break;

            case R.id.easy:

                if (secs >= 40) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    goBack(secs, testComplete);
                } else if (secs <= 20) {
                    secs *= 1.5;
                    goBack(secs, testComplete);
                } else {
                    secs *= 1.25;
                    goBack(secs, testComplete);
                }

                break;

            case R.id.hard:

                if (secs >= 40) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    goBack(secs, testComplete);
                } else if (secs >= 20) {
                    secs *= 0.8;
                    testComplete = true;
                    goBack(secs, testComplete);
                } else {
                    secs *= 0.6;
                    testComplete = true;
                    goBack(secs, testComplete);
                }

            case R.id.impossible:
                if (secs < 10) {
                    Toast.makeText(getApplicationContext(), "it's better to change exercise", Toast.LENGTH_SHORT).show();
                } else {
                    secs *= 0.75;
                    goBack(secs, testComplete);
                }

        }

    }


    private void goBack(int seconds, boolean testCompleted) {
        seconds = seconds * 1000;
        Intent result = new Intent();
        result.putExtra("milliseconds", seconds);
        result.putExtra("testCompleted", testCompleted);
        result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        setResult(Activity.RESULT_OK, result);
        finish();
    }




}
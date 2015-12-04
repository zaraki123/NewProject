package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 01/12/2015.
 */
public class TestResult extends AppCompatActivity {

    private static final int MAX_SETS = 5;
    private int secs = 0;
    private boolean testComplete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_test);
        Bundle extras = getIntent().getExtras();
        secs = extras.getInt("result");

    }

    public void result(View view) {
        switch (view.getId()) {
            case R.id.very_easy:
                if (secs <= 10) {
                    secs *= 2;
                    goBack(secs, testComplete);
                } else {
                    secs *= 1.5;
                    goBack(secs, testComplete);
                }

                break;

            case R.id.easy:

                if (secs <= 20) {
                    secs *= 1.5;
                    goBack(secs, testComplete);
                } else {
                    secs *= 1.25;
                    goBack(secs, testComplete);
                }

                break;

            case R.id.hard:
                if (secs >= 20) {
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
        Intent send_mecs = new Intent();
        send_mecs.putExtra("milliseconds", seconds);
        send_mecs.putExtra("testCompleted", testCompleted);
        setResult(Activity.RESULT_OK, send_mecs);
        finish();
    }




}
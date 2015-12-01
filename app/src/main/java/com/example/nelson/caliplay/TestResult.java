package com.example.nelson.caliplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Zaraki on 01/12/2015.
 */
public class TestResult extends AppCompatActivity {

    private static final int MAX_SETS = 5;
    private int[] testing = new int[MAX_SETS];
    private int secs = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_test);

        for (int sets = 0; sets < MAX_SETS; sets++) {
            testing[sets] = secs;
        }


    }

    public int result(View view) {
        switch (view.getId()) {
            case R.id.very_easy:
                if (secs <= 10) {
                    secs *= 2;
                } else {
                    secs *= 1.5;
                }

                break;

            case R.id.easy:

                if (secs <= 20) {
                    secs *= 1.5;
                } else {
                    secs *= 1.25;
                }

                break;

            case R.id.normal:
                break;

            case R.id.hard:
                if (secs >= 20) {
                    secs *= 0.8;
                } else {
                    secs *= 0.6;
                }

            case R.id.impossible:
                if (secs < 10) {
                    Toast.makeText(getApplicationContext(), "it's better to change exercise", Toast.LENGTH_SHORT).show();
                }

        }
        return secs;
    }


}
package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.ExerciseProfileTest;
import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class StartingTest extends AppCompatActivity {

    private MyApplication app;
    private int msecs = 10000;
    private int secs = 0;
    private TextView result;
    private ExerciseProfileTest exerciseProfileTest;
    private Exercise exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_test);
        result = (TextView)findViewById(R.id.seconds);
        app = (MyApplication) getApplication();
    }

    public void start(View view) {

        switch (view.getId()) {

            case R.id.startCoreTest:
                startTimer(msecs);
                break;

            case R.id.startPullTest:
                startTimer(msecs);
                break;

            case R.id.startPushTest:
                startTimer(msecs);
                break;


        }
    }

    private void startTimer(int msecs) {
        Intent startTest = new Intent(this, Timer.class);
        startTest.putExtra("msecs", msecs);
        if (startTest.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startTest, 1);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                secs = data.getIntExtra("seconds", 1);


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }
}

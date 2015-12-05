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
import com.example.nelson.caliplay.YouTube;
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
    private String tuckHollow= "6i1LsA5NDMQ";


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
                startVideo(tuckHollow);
                break;

            case R.id.startPullTest:
                break;

            case R.id.startPushTest:
                break;


        }
    }

    private void startVideo (String videoId) {
        Intent startVideo = new Intent(this, YouTube.class);
        startVideo.putExtra("videoId", videoId);
        startVideo.putExtra("msecs", msecs);
        if (startVideo.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startVideo, 1);
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                secs = data.getIntExtra("seconds", 1);
                result.setText(String.valueOf(secs));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }
}

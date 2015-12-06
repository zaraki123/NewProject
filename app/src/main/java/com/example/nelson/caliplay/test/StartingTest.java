package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.ExerciseProfileTest;
import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.YouTube;
import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class StartingTest extends AppCompatActivity {

    private MyApplication app;
    private int msecs = 30000;
    private int secs = 0;
    private int exerciseSelected = 0;
    private TextView result, nextLevel;
    private Button next;
    private ExerciseProfileTest exerciseProfileTest = new ExerciseProfileTest();
    private ArrayList<Exercise> coreExerciseList = new ArrayList<>();
    private ArrayList<Exercise> pullExerciseList = new ArrayList<>();
    private boolean exerciseCompleted = false, testCompleted = false;
    private MediaPlayer exerciseCompletedSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_test);
        result = (TextView) findViewById(R.id.seconds);
        app = (MyApplication) getApplication();
        next = (Button) findViewById(R.id.next);
        next.setVisibility(Button.GONE);
        nextLevel = (TextView) findViewById(R.id.textViewLevelCompleted);
        nextLevel.setVisibility(View.GONE);
        exerciseCompletedSound = MediaPlayer.create(this, R.raw.tadaaa);
        coreExerciseInitialization();
        pullExerciseInitialization();
    }

    public void start(View view) {

        switch (view.getId()) {

            case R.id.startCoreTest:
                startVideo(coreExerciseList.get(exerciseSelected).getVideoId());
                break;

            case R.id.startPullTest:
                break;

            case R.id.startPushTest:
                break;


        }
    }

    private void startVideo(String videoId) {
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
                exerciseCompleted = data.getBooleanExtra("exerciseCompleted", false);
                testCompleted = data.getBooleanExtra("testCompleted", false);
                result.setText(String.valueOf(secs));
                if (exerciseCompleted) {
                    next.setVisibility(Button.VISIBLE);
                    nextLevel.setVisibility(Button.VISIBLE);
                    exerciseCompletedSound.start();
                    exerciseSelected++;
                    startVideo(coreExerciseList.get(exerciseSelected).getVideoId());
                } else if (testCompleted) {
                    app.getDataManager().saveExercise(coreExerciseList.get(exerciseSelected));


                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
                }
            }
        }
    }
    private void coreExerciseInitialization() {
        coreExerciseList.add(exerciseProfileTest.getTuckHollow1());
        coreExerciseList.add(exerciseProfileTest.getTuckHollow2());
        coreExerciseList.add(exerciseProfileTest.getTuckHollow3());
        coreExerciseList.add(exerciseProfileTest.getHollow1());
        coreExerciseList.add(exerciseProfileTest.getHollow2());
        coreExerciseList.add(exerciseProfileTest.getHollow3());
    }

    private void pullExerciseInitialization() {
        pullExerciseList.add(exerciseProfileTest.getRowPullup1());
        pullExerciseList.add(exerciseProfileTest.getRowPullup2());
        pullExerciseList.add(exerciseProfileTest.getRowPullup3());

    }

    public void next(View view) {

    }
}

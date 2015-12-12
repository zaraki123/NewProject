package com.example.nelson.caliplay.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.YouTube;
import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.timer.RestTimer;

import java.util.ArrayList;

/**
 * Created by Zaraki on 01/12/2015.
 */
public class TestFeedback extends AppCompatActivity {

    private int secs = 10, reps = 7, exerciseLevel = 0;
    private boolean testComplete = false;
    private ArrayList<Exercise> exerciseList;
    private Button veryEasy, easy, hard, impossible;
    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_feedback);

        question = (TextView) findViewById(R.id.question);
        veryEasy = (Button) findViewById(R.id.very_easy);
        easy = (Button) findViewById(R.id.easy);
        hard = (Button) findViewById(R.id.hard);
        impossible = (Button) findViewById(R.id.impossible);


        // Get everything you need from the bundle
        Bundle extras = getIntent().getExtras();
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
        }
        lookVideo();
    }

    public void lookVideo() {
        Intent video = new Intent(TestFeedback.this, YouTube.class);
        video.putExtra("reps", reps);
        video.putExtra("secs", secs);
        video.putExtra("exerciseLevel", exerciseLevel);
        video.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        if (video.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(video, 1);
        }
    }

    public void result(View view) {
        switch (view.getId()) {
            case R.id.very_easy:

                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs <= 10) {
                    secs *= 2;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps <= 4) {
                    reps *= 2;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs >= 10 && secs <= 20)) {
                    secs *= 1.8;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps >= 5 && reps <= 10)) {
                    reps += 5;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs > 20 && secs < 40)) {
                    secs *= 1.5;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps > 10)) {
                    reps += 4;
                    feedBackDynamic(reps);
                }

                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs >= 40) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps >= 12) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    feedBackDynamic(reps);

                }


                break;

            case R.id.easy:

                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs <= 10) {
                    secs *= 1.5;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps <= 4) {
                    reps += 2;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs >= 10 && secs <= 20)) {
                    secs *= 1.4;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps >= 5 && reps <= 10)) {
                    reps += 3;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs > 20 && secs < 40)) {
                    secs *= 1.5;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps > 10)) {
                    reps += 2;
                    feedBackDynamic(reps);
                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs >= 40) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps >= 12) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    feedBackDynamic(reps);

                }


                break;

            case R.id.hard:

                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs <= 10) {
                    secs *= 0.8;
                    testComplete = true;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps <= 7) {
                    reps -= 2;
                    testComplete = true;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs >= 10 && secs <= 20)) {
                    secs *= 0.8;
                    testComplete = true;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps >= 8 && reps <= 12)) {
                    reps -= 2;
                    testComplete = true;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && (secs > 20 && secs < 40)) {
                    secs *= 0.8;
                    testComplete = true;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && (reps > 10)) {
                    reps -= 2;
                    testComplete = true;
                    feedBackDynamic(reps);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric") && secs >= 40) {
                    testComplete = true;
                    secs *= 0.8;
                    feedBackIsometric(secs);

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps >= 15) {
                    exerciseList.get(exerciseLevel).setCompleted(1);
                    testComplete = true;
                    reps -= 2;
                    feedBackDynamic(reps);
                }

            case R.id.impossible:
                if (secs < 10) {
                    Toast.makeText(getApplicationContext(), "it's better to change exercise", Toast.LENGTH_SHORT).show();
                } else {
                    secs *= 0.75;

                }

        }

    }


    private void feedBackIsometric(int seconds) {
        if (testComplete || (seconds >= 40)) {
            Intent result = new Intent(this, StartingTest.class);
            result.putExtra("seconds", seconds);
            result.putExtra("testCompleted", testComplete);
            result.putExtra("exerciseLevel", exerciseLevel);
            result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
            if (result.resolveActivity(getPackageManager()) != null) {
                setResult(RESULT_OK, result);
                finish();
            }
        } else {
            Intent result = new Intent(this, RestTimer.class);
            result.putExtra("seconds", seconds);
            result.putExtra("testCompleted", testComplete);
            result.putExtra("exerciseLevel", exerciseLevel);
            result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
            if (result.resolveActivity(getPackageManager()) != null) {
                startActivity(result);
            }

        }

    }

    private void feedBackDynamic(int reps) {
        // If test is completed or exercise is completed
        if (testComplete || (reps >= 12)) {
            Intent result = new Intent(TestFeedback.this, StartingTest.class);
            result.putExtra("reps", reps);
            result.putExtra("testCompleted", testComplete);
            result.putExtra("exerciseLevel", exerciseLevel);
            result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
            if (result.resolveActivity(getPackageManager()) != null) {
                setResult(RESULT_OK, result);
                finish();
            }
        } else {
            Intent result = new Intent(this, RestTimer.class);
            result.putExtra("reps", reps);
            result.putExtra("testCompleted", testComplete);
            result.putExtra("exerciseLevel", exerciseLevel);
            result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
            if (result.resolveActivity(getPackageManager()) != null) {
                startActivity(result);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                question.setText("How was it?");


            } else if (resultCode == RESULT_CANCELED) {
                finish();
            }

        }


    }


}
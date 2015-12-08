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
public class TestFeedback extends AppCompatActivity {

    private int secs = 0, reps = 0, exerciseLevel = 0;
    private boolean testComplete = false;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_analysis);
        Bundle extras = getIntent().getExtras();
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        secs = extras.getInt("secs");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
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

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps >= 15) {
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

                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic") && reps >= 15) {
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
        Intent result = new Intent();
        result.putExtra("milliseconds", seconds * 1000);
        result.putExtra("testCompleted", testComplete);
        result.putExtra("exerciseLevel", exerciseLevel);
        result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    private void feedBackDynamic(int reps) {
        Intent result = new Intent();
        result.putExtra("reps", reps);
        result.putExtra("testCompleted", testComplete);
        result.putExtra("exerciseLevel", exerciseLevel);
        result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        setResult(Activity.RESULT_OK, result);
        finish();
    }


}
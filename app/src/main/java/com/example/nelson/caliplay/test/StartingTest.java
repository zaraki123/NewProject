package com.example.nelson.caliplay.test;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.ExerciseProfileTest;
import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Zaraki on 03/12/2015.
 */

public class StartingTest extends AppCompatActivity {

    private MyApplication app;
    private int secs = 10, reps = 7;
    private int exerciseLevel = 1;
    private ImageView checkedCore,  checkedPull, checkedPush,  checkedSquat,  checkedCalf;
    private ImageButton coreTest, pullTest, pushTest, squatTest, calfTest;
    private TextView coreLevel, pullLevel, pushLevel, squatLevel, calfLevel;
    private ExerciseProfileTest exerciseProfileTest = new ExerciseProfileTest();
    private ArrayList<Exercise> coreExerciseList = new ArrayList<>();
    private ArrayList<Exercise> pullExerciseList = new ArrayList<>();
    private ArrayList<Exercise> pushExerciseList = new ArrayList<>();
    private ArrayList<Exercise> squatExerciseList = new ArrayList<>();
    private ArrayList<Exercise> calfExerciseList = new ArrayList<>();
    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private boolean exerciseCompleted = false, testCompleted = false;
    private MediaPlayer exerciseCompletedSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_test);
        app = (MyApplication) getApplication();

    // Imageviews to indicate the test is completed
        checkedCore = (ImageView) findViewById(R.id.checkCore);
        checkedCore.setVisibility(View.GONE);
        checkedPull = (ImageView) findViewById(R.id.checkPull);
        checkedPull.setVisibility(View.GONE);
        checkedPush = (ImageView) findViewById(R.id.checkPush);
        checkedPush.setVisibility(View.GONE);
        checkedSquat = (ImageView) findViewById(R.id.checkSquat);
        checkedSquat.setVisibility(View.GONE);
        checkedCalf = (ImageView) findViewById(R.id.checkCalf);
        checkedCalf.setVisibility(View.GONE);

        coreTest = (ImageButton) findViewById(R.id.startCoreTest);
        pullTest = (ImageButton) findViewById(R.id.startPullTest);
        pushTest = (ImageButton) findViewById(R.id.startPushTest);
        squatTest = (ImageButton) findViewById(R.id.startSquatTest);
        calfTest = (ImageButton) findViewById(R.id.startCalfTest);

        // Text level
        coreLevel = (TextView) findViewById(R.id.coreTestResult);
        pullLevel = (TextView) findViewById(R.id.pullTestResult);
        pushLevel = (TextView) findViewById(R.id.pushTestResult);
        squatLevel = (TextView) findViewById(R.id.squatTestResult);
        calfLevel = (TextView) findViewById(R.id.calfTestResult);

        // Sound of test complete
        exerciseCompletedSound = MediaPlayer.create(this, R.raw.tadaaa);

        // Exercise initialization
        coreExerciseInitialization();
        pullExerciseInitialization();
        pushExerciseInitialization();
        squatExerciseInitialization();
        calfExerciseInitialziation();

        // If the class is called after after a test





    }

    public void start(View view) {

        switch (view.getId()) {

            case R.id.startCoreTest:
                exerciseLevel = 1;
                startTestFeedBack(coreExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPullTest:
                exerciseLevel = 1;
                startTestFeedBack(pullExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPushTest:
                exerciseLevel = 1;
                startTestFeedBack(pushExerciseList, exerciseLevel - 1);
                break;

            case R.id.startSquatTest:
                exerciseLevel = 1;
                startTestFeedBack(squatExerciseList, exerciseLevel - 1);
                break;

            case R.id.startCalfTest:
                exerciseLevel = 1;
                startTestFeedBack(calfExerciseList, exerciseLevel - 1);
                break;

        }

    }

    private void startTestFeedBack(ArrayList<Exercise> exerciseList, int exerciseLevel) {
        Intent startTestFeedback = new Intent(this, TestFeedback.class);
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            startTestFeedback.putExtra("secs", secs);
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            startTestFeedback.putExtra("reps", reps);
        } else {
            Toast.makeText(getApplicationContext(), "Qualcosa è andato storto", Toast.LENGTH_LONG).show();
        }
        startTestFeedback.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        startTestFeedback.putExtra("exerciseLevel", exerciseLevel);
        if (startTestFeedback.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startTestFeedback, 1);
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

    private void pushExerciseInitialization() {
        pushExerciseList.add(exerciseProfileTest.getHollowPlank1());
        pushExerciseList.add(exerciseProfileTest.getHollowPlank2());
        pushExerciseList.add(exerciseProfileTest.getHollowPlank3());
    }

    private void squatExerciseInitialization() {
        squatExerciseList.add(exerciseProfileTest.getSquat1());
        squatExerciseList.add(exerciseProfileTest.getSquat2());
        squatExerciseList.add(exerciseProfileTest.getSquat3());
    }

    private void calfExerciseInitialziation() {
        calfExerciseList.add(exerciseProfileTest.getCalf1());
        calfExerciseList.add(exerciseProfileTest.getCalf2());
        calfExerciseList.add(exerciseProfileTest.getCalf3());
        calfExerciseList.add(exerciseProfileTest.getCalf4());
    }

    public void next(View view) {

        switch (exerciseList.get(exerciseLevel).getTypeOfMovement()) {
            case "Core":
                startTestFeedBack(coreExerciseList, exerciseLevel - 1);
                break;

            case "Pulling":
                startTestFeedBack(pullExerciseList, exerciseLevel - 1);
                break;

            case "Pushing":
                startTestFeedBack(pushExerciseList, exerciseLevel - 1);
                break;

            case "Squat":
                startTestFeedBack(squatExerciseList, exerciseLevel - 1);
                break;

            case "Calf":
                startTestFeedBack(calfExerciseList, exerciseLevel - 1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                exerciseLevel = data.getIntExtra("exerciseLevel", 1);
                testCompleted = data.getBooleanExtra("testCompleted", false);
                exerciseList = data.getParcelableArrayListExtra("exerciseArrayList");
                if (exerciseList.get(exerciseLevel).getCompleted() == 1) {
                    exerciseCompleted = true;
                }
                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
                    secs = data.getIntExtra("seconds", 1);
                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
                    reps = data.getIntExtra("reps", 1);
                }

                // The exercise is complete, and the user needs to try the next level of progression
                if (exerciseCompleted) {
                    exerciseLevel++;
                    exerciseCompletedSound.start();
                }

                // The test was completed, and the user can choose another one
                if (testCompleted) {
                    switch (exerciseList.get(exerciseLevel).getTypeOfMovement()) {
                        case "Core":
                            coreTest.setVisibility(View.GONE);
                            checkedCore.setVisibility(View.VISIBLE);
                            coreLevel.setText("Lev " + (exerciseLevel+1) + " " + secs + "s");
                            break;
                        case "Pulling":
                            pullTest.setVisibility(View.GONE);
                            checkedPull.setVisibility(View.VISIBLE);
                            pullLevel.setText("Level " + (exerciseLevel+1));
                            break;
                        case "Pushing":
                            pushTest.setVisibility(View.GONE);
                            checkedPush.setVisibility(View.VISIBLE);
                            pushLevel.setText("Level " + (exerciseLevel+1));
                            break;
                        case "Squat":
                            squatTest.setVisibility(View.GONE);
                            checkedSquat.setVisibility(View.VISIBLE);
                            squatLevel.setText("Level " + (exerciseLevel + 1));
                            break;
                        case "Calf":
                            calfTest.setVisibility(View.GONE);
                            checkedCalf.setVisibility(View.VISIBLE);
                            calfLevel.setText("Level " + (exerciseLevel + 1));
                            break;
                    }
                    exerciseCompletedSound.start();
                    app.getDataManager().saveExercise(exerciseList.get(exerciseLevel));
                }
            }
            }

            if (resultCode == RESULT_CANCELED) {
                System.out.println("something went wrong");
            }

        }
    }



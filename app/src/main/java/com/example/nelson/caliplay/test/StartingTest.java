package com.example.nelson.caliplay.test;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private ImageView checkedCore, checkedPull, checkedPush, checkedSquat, checkedCalf;
    private ImageButton coreTest, pullTest, pushTest, squatTest, calfTest;
    private ExerciseProfileTest exerciseProfileTest = new ExerciseProfileTest();
    private ArrayList<Exercise> coreExerciseList = new ArrayList<>();
    private ArrayList<Exercise> pullExerciseList = new ArrayList<>();
    private ArrayList<Exercise> pushExerciseList = new ArrayList<>();
    private ArrayList<Exercise> squatExerciseList = new ArrayList<>();
    private ArrayList<Exercise> calfExerciseList = new ArrayList<>();
    private ArrayList<Exercise> exerciseList = new ArrayList<>();
    private boolean exerciseCompleted = false, testCompleted = false;
    private MediaPlayer exerciseCompletedSound;
    private RelativeLayout coreRelative, pullRelative, pushRelative, squatRelative, calfRelative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_test);
        app = (MyApplication) getApplication();


        // Imageviews to indicate the test is completed
        checkedCore = (ImageView) findViewById(R.id.checkCore);
        checkedCore.setVisibility(View.INVISIBLE);
        checkedPull = (ImageView) findViewById(R.id.checkPull);
        checkedPull.setVisibility(View.INVISIBLE);
        checkedPush = (ImageView) findViewById(R.id.checkPush);
        checkedPush.setVisibility(View.INVISIBLE);
        checkedSquat = (ImageView) findViewById(R.id.checkSquat);
        checkedSquat.setVisibility(View.INVISIBLE);
        checkedCalf = (ImageView) findViewById(R.id.checkCalf);
        checkedCalf.setVisibility(View.INVISIBLE);

        coreRelative = (RelativeLayout) findViewById(R.id.relativeCore);
        pullRelative = (RelativeLayout) findViewById(R.id.relativePull);
        pushRelative = (RelativeLayout) findViewById(R.id.relativePush);
        squatRelative = (RelativeLayout) findViewById(R.id.relativeSquat);
        calfRelative = (RelativeLayout) findViewById(R.id.relativeCalf);

        coreTest = (ImageButton) findViewById(R.id.startCoreTest);
        pullTest = (ImageButton) findViewById(R.id.startPullTest);
        pushTest = (ImageButton) findViewById(R.id.startPushTest);
        squatTest = (ImageButton) findViewById(R.id.startSquatTest);
        calfTest = (ImageButton) findViewById(R.id.startCalfTest);


        // Sound of test complete
        exerciseCompletedSound = MediaPlayer.create(this, R.raw.tadaaa);

        // Exercise initialization
        coreExerciseInitialization();
        pullExerciseInitialization();
        pushExerciseInitialization();
        squatExerciseInitialization();
        calfExerciseInitialziation();

    }



    public void startTest(View view) {

        switch (view.getId()) {

            case R.id.startCoreTest:
            case R.id.relativeCore:
                exerciseLevel = 1;
                startTestFeedBack(coreExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPullTest:
            case R.id.relativePull:
                exerciseLevel = 1;
                startTestFeedBack(pullExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPushTest:
            case R.id.relativePush:
                exerciseLevel = 1;
                startTestFeedBack(pushExerciseList, exerciseLevel - 1);
                break;

            case R.id.startSquatTest:
            case R.id.relativeSquat:
                exerciseLevel = 1;
                startTestFeedBack(squatExerciseList, exerciseLevel - 1);
                break;

            case R.id.startCalfTest:
            case R.id.relativeCalf:
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
                            checkedCore.setVisibility(View.VISIBLE);
                            break;
                        case "Pulling":
                            checkedPull.setVisibility(View.VISIBLE);
                            break;
                        case "Pushing":
                            checkedPush.setVisibility(View.VISIBLE);
                            break;
                        case "Squat":
                            checkedSquat.setVisibility(View.VISIBLE);
                            break;
                        case "Calf":
                            checkedCalf.setVisibility(View.VISIBLE);
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



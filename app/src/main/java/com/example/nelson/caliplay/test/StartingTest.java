package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
    private int secs = 10, reps = 7;
    private int exerciseLevel = 1;
    private TextView result, test;
    private Button coreTest, pullTest, pushTest, squatTest, calfTest, next;
    private String typeOfMovement;
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
        result = (TextView) findViewById(R.id.resultTest);
        app = (MyApplication) getApplication();
        test = (TextView) findViewById(R.id.test);
        test.setVisibility(View.GONE);
        coreTest = (Button) findViewById(R.id.startCoreTest);
        pullTest = (Button) findViewById(R.id.startPullTest);
        pushTest = (Button) findViewById(R.id.startPushTest);
        squatTest = (Button) findViewById(R.id.startSquatTest);
        calfTest = (Button) findViewById(R.id.startCalfTest);
        next = (Button) findViewById(R.id.next);
        next.setVisibility(Button.GONE);
        exerciseCompletedSound = MediaPlayer.create(this, R.raw.tadaaa);
        coreExerciseInitialization();
        pullExerciseInitialization();
        pushExerciseInitialization();
        squatExerciseInitialization();
        calfExerciseInitialziation();
    }

    public void start(View view) {

        switch (view.getId()) {

            case R.id.startCoreTest:
                exerciseLevel = 1;
                startVideo(coreExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPullTest:
                exerciseLevel = 1;
                startVideo(pullExerciseList, exerciseLevel - 1);
                break;

            case R.id.startPushTest:
                exerciseLevel = 1;
                startVideo(pushExerciseList, exerciseLevel - 1);
                break;

            case R.id.startSquatTest:
                exerciseLevel = 1;
                startVideo(squatExerciseList, exerciseLevel - 1);
                break;

            case R.id.startCalfTest:
                exerciseLevel = 1;
                startVideo(calfExerciseList, exerciseLevel - 1);
                break;

        }
    }

    private void startVideo(ArrayList<Exercise> exerciseList, int exerciseLevel) {
        Intent startVideo = new Intent(this, YouTube.class);
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            startVideo.putExtra("secs", secs);
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            startVideo.putExtra("reps", reps);
        } else {
            Toast.makeText(getApplicationContext(), "Qualcosa è andato storto", Toast.LENGTH_LONG).show();
        }
        startVideo.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        startVideo.putExtra("exerciseLevel", exerciseLevel);
        if (startVideo.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startVideo, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                exerciseLevel = data.getIntExtra("exerciseLevel", 1);
                testCompleted = data.getBooleanExtra("testCompleted", false);
                exerciseList = data.getParcelableArrayListExtra("exerciseArrayList");
                exerciseCompleted = (exerciseList.get(exerciseLevel).getCompleted() != 0);
                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
                    secs = data.getIntExtra("secs", 1);
                } else {
                    reps = data.getIntExtra("reps", 1);
                }

                // The exercise is complete, and the user needs to try the next level of progression
                if (exerciseCompleted) {
                    test.setVisibility(View.VISIBLE);
                    test.setText(exerciseList.get(exerciseLevel).getTypeOfMovement() + " exercise completed! \nLet's try the next level");
                    next.setVisibility(View.VISIBLE);
                    next.setText("Next level");
                    exerciseLevel++;
                    exerciseCompletedSound.start();
                }

                // The test was completed, and the user can choose another one
                if (testCompleted) {
                    switch (exerciseList.get(exerciseLevel).getTypeOfMovement()) {
                        case "Core":
                            coreTest.setText("COMPLETED");
                            coreTest.setBackgroundColor(Color.parseColor("#80ff00"));
                            test.setVisibility(View.VISIBLE);
                            break;
                        case "Pulling":
                            pullTest.setText("COMPLETED");
                            pullTest.setBackgroundColor(Color.parseColor("#80ff00"));
                            test.setVisibility(View.VISIBLE);
                            break;
                        case "Pushing":
                            pushTest.setText("COMPLETED");
                            pushTest.setBackgroundColor(Color.parseColor("#80ff00"));
                            test.setVisibility(View.VISIBLE);
                            break;
                        case "Squat":
                            squatTest.setText("COMPLETED");
                            squatTest.setBackgroundColor(Color.parseColor("#80ff00"));
                            test.setVisibility(View.VISIBLE);
                            break;
                        case "Calf":
                            calfTest.setText("COMPLETED");
                            calfTest.setBackgroundColor(Color.parseColor("#80ff00"));
                            test.setVisibility(View.VISIBLE);
                            break;
                    }
                    test.setText(exerciseList.get(exerciseLevel).getTypeOfMovement() + " test completed! \nChoose another test");
                    if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
                        result.setText(String.valueOf(secs));
                    } else {
                        result.setText(String.valueOf(reps));;
                    }

                    exerciseCompletedSound.start();
                    app.getDataManager().saveExercise(coreExerciseList.get(exerciseLevel));
                }

            }

        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
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
                startVideo(coreExerciseList, exerciseLevel - 1);
                break;

            case "Pulling":
                startVideo(pullExerciseList, exerciseLevel - 1);
                break;

            case "Pushing":
                startVideo(pushExerciseList, exerciseLevel - 1);
                break;

            case "Squat":
                startVideo(squatExerciseList, exerciseLevel - 1);
                break;

            case "Calf":
                startVideo(calfExerciseList, exerciseLevel - 1);
                break;

        }
    }

}

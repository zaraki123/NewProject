package com.example.nelson.caliplay.timer;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.utils.CircularSeekBar;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Zaraki on 08/12/2015.
 */
public class RestTimer extends AppCompatActivity {

    private static final int REST = 30000;
    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, gun;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0, milliseconds = REST;
    private ImageButton startTimer, restartTimer, stopTimer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startTimer = (ImageButton) findViewById(R.id.startRestTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartRestTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopRestTimer);
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_rest_not_clickable));
        startTimer.setClickable(false);

        Bundle extras = getIntent().getExtras();

        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("seconds");
        }
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
        }

        // Sounds initialization
        clockTicking = MediaPlayer.create(this, R.raw.clock_ticking);
        racestart_sound_final_ticks = MediaPlayer.create(this, R.raw.race_start1);
        gun = MediaPlayer.create(this, R.raw.gun);

        // Display setting
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();
        introduction = (TextView) findViewById(R.id.introduction);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/android.ttf");
        introduction.setTypeface(face);
        introduction.setText("rest  for ");

        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);

        lastSeconds = (REST / 1000);
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(REST+1000);
        timer.start();

    }

    public void startRestTimer(View view) {
        timer.start();
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_rest_not_clickable));
        startTimer.setClickable(false);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.stop_rest_timer));
        stopTimer.setClickable(true);
        restartTimer.setClickable(true);
    }

    public void stopRestTimer(View view) {
        milliseconds = (lastSeconds - secsTimer)*1000;
        timer.cancel();
        timerInitialization(milliseconds);
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_rest_timer));
        startTimer.setClickable(true);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_stop_not_clickable));
        stopTimer.setClickable(false);
    }

    public void restartRestTimer(View view) {
        timer.cancel();
        timerInitialization(REST+1000);
        timer.start();
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_rest_not_clickable));
        startTimer.setClickable(false);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.stop_rest_timer));
        stopTimer.setClickable(true);
    }

    private void timerInitialization(int milliseconds) {

        if (milliseconds == REST*1000 || milliseconds == REST+1000) {
            secsTimer = 0;
            seekBar.setProgress(0);
            if (milliseconds == REST+1000) {
                lastSeconds = REST/1000 + 1;
            }
            seekBar.setMax(lastSeconds);
        }
        timer = new CountDownTimer(milliseconds, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                secsTimer++;
                timerDisplay.setText("" + (millisUntilFinished) / 1000);
                seekBar.setProgress(secsTimer);
                if (secsTimer >= (lastSeconds - 3) && secsTimer < lastSeconds) {
                    racestart_sound_final_ticks.start();

                } else {
                    clockTicking.start();
                }

            }

            @Override
            public void onFinish() {

                secsTimer++;
                seekBar.setProgress(secsTimer);
                clockTicking.release();
                racestart_sound_final_ticks.reset();
                timerDisplay.setText("0");
                gun.start();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gun.release();
                        startWorkoutTimer();
                    }
                }, 1000);


            }
        };
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            milliseconds = (lastSeconds - secsTimer)*1000;
            timer.cancel();
            timerInitialization(milliseconds);
            startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_rest_timer));
            startTimer.setClickable(true);
        }
    }

    private void startWorkoutTimer() {
        Intent workoutTimer = new Intent(this, WorkoutTimer.class);
        workoutTimer.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        workoutTimer.putExtra("exerciseLevel", exerciseLevel);
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            workoutTimer.putExtra("secs", secs);
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            workoutTimer.putExtra("reps", reps);
        } else {
            Toast.makeText(getApplicationContext(), "Qualcosa è andato storto", Toast.LENGTH_LONG).show();
        }
        if (workoutTimer.resolveActivity(getPackageManager()) != null) {
            startActivity(workoutTimer);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }

            else if (resultCode == RESULT_CANCELED) {
                finish();
            }

        }


    }
}

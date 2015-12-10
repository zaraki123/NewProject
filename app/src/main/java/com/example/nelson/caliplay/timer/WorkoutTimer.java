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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nelson.caliplay.utils.CircularSeekBar;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;

import java.util.ArrayList;

/**
 * Created by Zaraki on 08/12/2015.
 */
public class WorkoutTimer extends AppCompatActivity {

    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, racestart_last_tick;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0;
    private ImageButton startTimer, restartTimer, stopTimer;
    private int milliseconds;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartWorkoutTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopWorkoutTimer);
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.orange_dumbbell_not_clickable));
        startTimer.setClickable(false);

        Bundle extras = getIntent().getExtras();


        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
            lastSeconds = secs;
        }
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
            lastSeconds = reps * 3;

        }

        // Sounds initialization
        clockTicking = MediaPlayer.create(this, R.raw.clock_ticking);
        racestart_sound_final_ticks = MediaPlayer.create(this, R.raw.race_start1);
        racestart_last_tick = MediaPlayer.create(this, R.raw.race_start_last_tic);

        //Button initialization
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);


        // Display setting
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();
        introduction = (TextView) findViewById(R.id.introduction);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/android.ttf");
        introduction.setTypeface(face);
        introduction.setText("Goooo");

        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        lastSeconds = secs;
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(secs*1000+1000);
        timer.start();
    }

    public void startWorkoutTimer(View view) {
        timer.start();
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.stop_workout_timer));
        stopTimer.setClickable(true);
        restartTimer.setClickable(true);
    }

    public void stopWorkoutTimer(View view) {
        milliseconds = (lastSeconds - secsTimer)*1000;
        timer.cancel();
        timerInitialization(milliseconds);
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_workout_timer));
        startTimer.setClickable(true);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.orange_stop_not_clickable));
        stopTimer.setClickable(false);
    }

    public void restartWorkoutTimer(View view) {
        timer.cancel();
        timerInitialization(secs*1000+1000);
        timer.start();
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.orange_dumbbell_not_clickable));
        startTimer.setClickable(false);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.orange_stop_not_pressed));
        stopTimer.setClickable(true);
    }

    private void timerInitialization(int milliseconds) {


        if (milliseconds == secs*1000 || milliseconds == secs*1000+1000) {
            secsTimer = 0;
            seekBar.setProgress(0);
            if (milliseconds == secs*1000+1000) {
                lastSeconds = secs+1;
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
                racestart_last_tick.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        racestart_last_tick.release();
                        Intent backIntent = new Intent();
                        setResult(RESULT_OK, backIntent);
                        finish();
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
            startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_workout_timer));
            startTimer.setClickable(true);
        }

    }




}
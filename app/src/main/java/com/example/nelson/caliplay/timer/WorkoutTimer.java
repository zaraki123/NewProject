package com.example.nelson.caliplay.timer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.utils.CircularSeekBar;

import java.util.ArrayList;

/**
 * Created by Zaraki on 08/12/2015.
 */
public class WorkoutTimer extends AppCompatActivity {

    private static final int FIVE = 6000;
    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, racestart_last_tick;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0;
    private ImageButton startTimer, restartTimer, stopTimer, fastTimer;
    private int milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartWorkoutTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopWorkoutTimer);
        fastTimer = (ImageButton) findViewById(R.id.startFastWorkoutTimer);
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekBar.setOnSeekBarChangeListener(new CircleSeekBarListener());

        introduction = (TextView) findViewById(R.id.introduction);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/droid_serif_bold.ttf");
        introduction.setTypeface(face);


        Bundle extras = getIntent().getExtras();
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
            lastSeconds = secs;
        }
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
            secs = reps * 3;

        }

        // Sounds initialization
        clockTicking = MediaPlayer.create(this, R.raw.tick2);
        racestart_sound_final_ticks = MediaPlayer.create(this, R.raw.beep);
        racestart_last_tick = MediaPlayer.create(this, R.raw.race_start_last_tic);
        racestart_last_tick.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                racestart_last_tick.release();
                Intent backIntent = new Intent();
                setResult(RESULT_OK, backIntent);
                finish();
            }
        });


        //Button initialization
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);


        // TimerDisplay
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();




        lastSeconds = secs;
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(secs * 1000 + 1000);
        timer.start();
    }

    public void startWorkoutTimer(View view) {
        startTimer();
    }

    public void stopWorkoutTimer(View view) {
        stopTimer();
    }

    public void restartWorkoutTimer(View view) {
        timer.cancel();
        timerInitialization(secs * 1000 + 1000);
        timer.start();
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
    }

    public void startFastWorkoutTimer(View view) {
        timer.cancel();
        timerInitialization(FIVE);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        seekBar.setCircleProgressColor(Color.parseColor("#ffa600"));
        seekBar.setPointerHaloColor(Color.parseColor("#ae0300"));
        timer.start();
    }

    public void workoutTimerDisplay(View view) {
        if (stopTimer.isClickable()) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void timerInitialization(int milliseconds) {


        if (milliseconds == secs * 1000 || milliseconds == secs * 1000 + 1000) {
            secsTimer = 0;
            seekBar.setProgress(0);
            if (milliseconds == secs * 1000 + 1000) {
                lastSeconds = secs + 1;
            }
            seekBar.setMax(lastSeconds);
        } else if (milliseconds == FIVE) {
            secsTimer = lastSeconds - FIVE / 1000;
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

            }
        };

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            milliseconds = (lastSeconds - secsTimer) * 1000;
            timer.cancel();
            timerInitialization(milliseconds);
            startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_workout_timer));
            startTimer.setClickable(true);
        }

    }

    private void startTimer() {
        timer.start();
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.redo_workout_timer));
        restartTimer.setClickable(true);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        seekBar.setCircleProgressColor(Color.parseColor("#ffa600"));
        seekBar.setPointerHaloColor(Color.parseColor("#ae0300"));
    }

    private void stopTimer() {
        milliseconds = (lastSeconds - secsTimer) * 1000;
        timer.cancel();
        timerInitialization(milliseconds);
        seekBar.setCircleProgressColor(Color.parseColor("#ae0300"));
        seekBar.setPointerHaloColor(Color.parseColor("#ffa600"));
        startTimer.setVisibility(View.VISIBLE);
        startTimer.setClickable(true);
        stopTimer.setVisibility(View.INVISIBLE);
        stopTimer.setClickable(false);
    }


}


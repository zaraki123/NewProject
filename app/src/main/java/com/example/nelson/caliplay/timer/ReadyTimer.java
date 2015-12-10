package com.example.nelson.caliplay.timer;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.utils.CircularSeekBar;

import java.util.ArrayList;

/**
 * Created by Zaraki on 08/12/2015.
 */

public class ReadyTimer extends AppCompatActivity {

    private static final int READY = 10000;
    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, gun;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0;
    private ImageButton startTimer, restartTimer, stopTimer;
    private int milliseconds = READY;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Bundle extras = getIntent().getExtras();
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartRestTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopReadyTimer);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_stop_not_clickable));
        stopTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_redo_not_clickable));
        restartTimer.setClickable(false);


        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
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
        introduction.setText("Ten  seconds  to  go");

        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        lastSeconds = (READY / 1000);
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(milliseconds);

    }

    public void startReadyTimer(View view) {
        timer.start();
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_dumbbell_not_clickable));
        startTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.redo_ready_timer));
        restartTimer.setClickable(true);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.stop_ready_timer));
        stopTimer.setClickable(true);
        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
    }

    public void stopReadyTimer(View view) {
        milliseconds = (lastSeconds - secsTimer) * 1000;
        timer.cancel();
        timerInitialization(milliseconds);
        seekBar.setCircleProgressColor(Color.parseColor("#FF001B05"));
        seekBar.setPointerHaloColor(Color.parseColor("#80ff00"));
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.start_ready_timer));
        startTimer.setClickable(true);
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_stop_not_clickable));
        stopTimer.setClickable(false);
    }

    public void restartReadyTimer(View view) {
        timer.cancel();
        timerInitialization(READY+1000);
        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
        stopTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.stop_ready_timer));
        stopTimer.setClickable(true);
        startTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_dumbbell_not_clickable));
        startTimer.setClickable(false);
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            milliseconds = (lastSeconds - secsTimer) * 1000;
            timer.cancel();
            timerInitialization(milliseconds);
            startTimer.setVisibility(View.VISIBLE);
            startTimer.setClickable(true);
        }
    }

    private void timerInitialization(int milliseconds) {

        if (milliseconds == READY || milliseconds == 11000) {
            secsTimer = 0;
            seekBar.setProgress(0);
            if (milliseconds == 11000) {
                lastSeconds = 11;
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
                clockTicking.release();
                racestart_sound_final_ticks.release();
                seekBar.setProgress(secsTimer);
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


    private void startWorkoutTimer() {
        Intent workoutTimer = new Intent(ReadyTimer.this, WorkoutTimer.class);
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
            startActivityForResult(workoutTimer, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }

            if (resultCode == RESULT_CANCELED) {
                setResult(RESULT_CANCELED);
                    finish();
            }

        }

    }

}


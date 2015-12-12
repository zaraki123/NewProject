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
    private static final int FIVE = 6000;
    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, gun;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0, milliseconds = REST;
    private ImageButton startTimer, restartTimer, stopTimer, fastTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        startTimer = (ImageButton) findViewById(R.id.startRestTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartRestTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopRestTimer);
        fastTimer = (ImageButton) findViewById(R.id.startFastRestTimer);
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekBar.setOnSeekBarChangeListener(new CircleSeekBarListener());

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
        clockTicking = MediaPlayer.create(this, R.raw.tick2);
        racestart_sound_final_ticks = MediaPlayer.create(this, R.raw.beep);
        gun = MediaPlayer.create(this, R.raw.gun2);
        gun.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                gun.release();
                startWorkoutTimer();
            }
        });

        // Display setting


        // TimerDisplay
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();


        introduction = (TextView) findViewById(R.id.introduction);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/android.ttf");
        introduction.setTypeface(face);
        introduction.setText("rest  for ");


        lastSeconds = (REST / 1000);
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(REST+1000);
        timer.start();

    }

    public void startRestTimer(View view) {
        startTimer();
    }

    public void stopRestTimer(View view) {
       stopTimer();
    }

    public void restartRestTimer(View view) {
        timer.cancel();
        timerInitialization(REST + 1000);
        timer.start();
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
    }

    public void startFastRestTimer(View view) {
        timer.cancel();
        timerInitialization(FIVE);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        seekBar.setCircleProgressColor(Color.parseColor("#00d4ff"));
        seekBar.setPointerHaloColor(Color.parseColor("#000dff"));
        timer.start();
    }

    public void restTimerDisplay(View view) {
        if (stopTimer.isClickable()) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void timerInitialization(int milliseconds) {

        if (milliseconds == REST*1000 || milliseconds == REST+1000) {
            secsTimer = 0;
            seekBar.setProgress(0);
            if (milliseconds == REST+1000) {
                lastSeconds = REST/1000 + 1;
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
                gun.start();






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


    private void startTimer() {
        timer.start();
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.redo_rest_timer));
        restartTimer.setClickable(true);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        seekBar.setCircleProgressColor(Color.parseColor("#00d4ff"));
        seekBar.setPointerHaloColor(Color.parseColor("#000dff"));
    }

    private void stopTimer() {
        milliseconds = (lastSeconds - secsTimer) * 1000;
        timer.cancel();
        timerInitialization(milliseconds);
        seekBar.setCircleProgressColor(Color.parseColor("#000dff"));
        seekBar.setPointerHaloColor(Color.parseColor("#00d4ff"));
        startTimer.setVisibility(View.VISIBLE);
        startTimer.setClickable(true);
        stopTimer.setVisibility(View.INVISIBLE);
        stopTimer.setClickable(false);
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

            else if (resultCode == RESULT_CANCELED) {
                finish();
            }

        }


    }


}

package com.example.nelson.caliplay.timer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private static final int FIVE = 6000;
    private CountDownTimer timer;
    private CircularSeekBar seekBar;
    private ArrayList<Exercise> exerciseList;
    private TextView timerDisplay, introduction;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, gun;
    private int secs = 0, lastSeconds = 0, reps = 0, exerciseLevel = 0, secsTimer = 0;
    private ImageButton startTimer, restartTimer, stopTimer, fastTimer;
    private int milliseconds = READY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.ready_timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();

        introduction = (TextView) findViewById(R.id.introduction);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/droid_serif_bold.ttf");
        introduction.setTypeface(face);


        Bundle extras = getIntent().getExtras();
        startTimer = (ImageButton) findViewById(R.id.startWorkoutTimer);
        restartTimer = (ImageButton) findViewById(R.id.restartRestTimer);
        stopTimer = (ImageButton) findViewById(R.id.stopReadyTimer);
        fastTimer = (ImageButton) findViewById(R.id.startFastReadyTimer);
        fastTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_fast_not_clickable));
        fastTimer.setClickable(false);
        stopTimer.setVisibility(View.INVISIBLE);
        stopTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.green_redo_not_clickable));
        restartTimer.setClickable(false);

        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);


        seekBar.setOnSeekBarChangeListener(new CircleSeekBarListener());

        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
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
                mp.release();
                startWorkoutTimer();
            }
        });


        // TimerDisplay
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();

        lastSeconds = (READY / 1000);
        timerDisplay.setText("" + lastSeconds);
        secsTimer = 0;

        timerInitialization(milliseconds);

    }


    public void startReadyTimer(View view) {
        startTimer();
    }

    public void stopReadyTimer(View view) {
        stopTimer();
    }

    public void restartReadyTimer(View view) {
        timer.cancel();

        timerInitialization(READY + 1000);
        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        startTimer.setVisibility(View.INVISIBLE);
        timer.start();
    }

    public void startFastReadyTimer(View view) {
        timer.cancel();
        timerInitialization(FIVE);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.redo_ready_timer));
        restartTimer.setClickable(true);

        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            milliseconds = (lastSeconds - secsTimer) * 1000;
            timer.cancel();
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
                clockTicking.release();
                racestart_sound_final_ticks.release();
                seekBar.setProgress(secsTimer);
                timerDisplay.setText("0");
                gun.start();


            }
        };
    }


    public void readyTimerDisplay(View view) {

        if (stopTimer.isClickable()) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        timer.start();
        startTimer.setVisibility(View.INVISIBLE);
        startTimer.setClickable(false);
        restartTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.redo_ready_timer));
        restartTimer.setClickable(true);
        fastTimer.setBackground(ContextCompat.getDrawable(this, R.drawable.fast_ready_timer));
        fastTimer.setClickable(true);
        stopTimer.setVisibility(View.VISIBLE);
        stopTimer.setClickable(true);
        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
    }

    private void stopTimer() {
        milliseconds = (lastSeconds - secsTimer) * 1000;
        timer.cancel();
        timerInitialization(milliseconds);
        seekBar.setCircleProgressColor(Color.parseColor("#00500f"));
        seekBar.setPointerHaloColor(Color.parseColor("#80ff00"));
        startTimer.setVisibility(View.VISIBLE);
        startTimer.setClickable(true);
        stopTimer.setVisibility(View.INVISIBLE);
        stopTimer.setClickable(false);
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


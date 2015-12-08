package com.example.nelson.caliplay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.test.TestFeedback;

import java.util.ArrayList;

/**
 * Created by Zaraki on 01/12/2015.
 */

public class Timer extends AppCompatActivity {
    private static final int PAUSE = 30000;
    private static final int READY = 6000;
    private TextView introduction, timerDisplay, repsDisplay;
    private CircularSeekBar seekBar;
    private int secs = 0, lastSeconds = 0, exerciseLevel = 0;
    private int reps = 0;
    private CountDownTimer timer;
    private int msecs = 0, newMsecsTimer2 = 0, newMsecsTimer1 = 0;
    private MediaPlayer clockTicking, racestart_sound_final_ticks, racestart_last_tick;
    private boolean testCompleted = false, exerciseCompleted = false;

    private ArrayList<Exercise> exerciseList;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        introduction = (TextView) findViewById(R.id.introduction);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        timerDisplay = (TextView) findViewById(R.id.timer);
        repsDisplay = (TextView) findViewById(R.id.repsNumber);
        timerDisplay.bringToFront();
        clockTicking = MediaPlayer.create(this, R.raw.clock_ticking);
        racestart_sound_final_ticks = MediaPlayer.create(this, R.raw.race_start1);
        racestart_last_tick = MediaPlayer.create(this, R.raw.race_start_last_tic);

        Typeface face = Typeface.createFromAsset(getAssets(), "font/android.ttf");
        introduction.setTypeface(face);


        Bundle extras = getIntent().getExtras();
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");

        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            newMsecsTimer1 = extras.getInt("secs") * 1000;
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
            newMsecsTimer1 = reps * 3 * 1000; // each rep requires about 3s
        }
    }

    public void start(View view) {
        readyTimer(READY);
        timer.start();
    }

    // Timer to get ready in five seconds
    private void readyTimer(final int msecs) {
        secs = 0;
        lastSeconds = msecs / 1000;
        introduction.setText("Wait for");
        seekBar.setProgress(secs);
        seekBar.setMax(msecs / 1000);
        seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        seekBar.setPointerColor(Color.parseColor("#4c9700"));
        seekBar.setPointerHaloColor(Color.parseColor("#00500f"));
        timerDisplay.setText("" + lastSeconds);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait for 1s");
            }
        }, 1000);
        timer = new CountDownTimer(msecs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + (millisUntilFinished) / 1000);
                seekBar.setProgress(secs);
                if (secs >= (lastSeconds - 3) && secs < lastSeconds) {
                    racestart_sound_final_ticks.start();
                } else {
                    clockTicking.start();
                }
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);
                timerDisplay.setText("0");
                racestart_last_tick.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timerDisplay.setText("" + msecs / 1000);
                        workoutTimer(newMsecsTimer1);
                        timer.start();
                    }
                }, 500);


            }

        };
    }

    // Timer to do the exercise
    private void workoutTimer(int msecs) {
        secs = 0;
        lastSeconds = msecs / 1000;
        introduction.setText("GOOOO");
        seekBar.setProgress(secs);
        seekBar.setMax(msecs / 1000);
        seekBar.setCircleProgressColor(Color.parseColor("#ffa600"));
        seekBar.setPointerColor(Color.parseColor("#ff3700"));
        seekBar.setPointerHaloColor(Color.parseColor("#ae0300"));
        timer = new CountDownTimer(msecs, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + (millisUntilFinished) / 1000);
                seekBar.setProgress(secs);
                clockTicking.start();
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);
                timerDisplay.setText("0");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent result = new Intent(getApplicationContext(), TestFeedback.class);
                        result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
                        result.putExtra("secs", secs);
                        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
                            result.putExtra("reps", reps);
                        }
                        if (result.resolveActivity(getPackageManager()) != null) {
                            seekBar.setProgress(0);
                            startActivityForResult(result, 1);
                        }
                    }
                }, 1000);


            }

        };
    }


    // Timer to rest, and after which the workoutTimer starts
    private void pauseTimer(final int msecs) {
        secs = 0;

        seekBar.setProgress(secs);
        lastSeconds = msecs / 1000;
        introduction.setText("Rest for ");
        seekBar.setMax(msecs / 1000);
        seekBar.setCircleProgressColor(Color.parseColor("#00d4ff"));
        seekBar.setPointerColor(Color.parseColor("#0088ff"));
        seekBar.setPointerHaloColor(Color.parseColor("#000dff"));
        timerDisplay.setText("" + lastSeconds);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait for 1s");
            }
        }, 1000);
        timer = new CountDownTimer(msecs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + (millisUntilFinished) / 1000);
                seekBar.setProgress(secs);
                if (secs >= (lastSeconds - 3) && secs < lastSeconds) {
                    racestart_sound_final_ticks.start();
                } else {
                    clockTicking.start();
                }
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);
                timerDisplay.setText("0");
                racestart_last_tick.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timerDisplay.setText("" + newMsecsTimer2 / 1000);
                        workoutTimer(newMsecsTimer2);
                        timer.start();

                    }
                }, 1000);

            }

        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                exerciseLevel = data.getIntExtra("exerciseLevel", 1);
                testCompleted = data.getBooleanExtra("testCompleted", false);
                exerciseList = data.getParcelableArrayListExtra("exerciseArrayList");
                if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
                    newMsecsTimer2 = data.getIntExtra("milliseconds", 1);
                } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
                    reps = data.getIntExtra("reps", 1);
                    newMsecsTimer2 = reps * 3 * 1000;
                }
                if (exerciseList.get(exerciseLevel).getCompleted() == 1) {
                    exerciseCompleted = true;
                }


                // The isometric exercise or the test was completed, so it's time to go back
                if ((exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) && (exerciseCompleted || testCompleted)) {
                    secs = newMsecsTimer2 / 1000;
                    Intent result = new Intent();
                    result.putExtra("seconds", secs);
                    result.putExtra("testCompleted", testCompleted);
                    result.putExtra("exerciseLevel", exerciseLevel);
                    result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                }

                // The dynamic exercise or the test was completed, so it's time to go back
                if ((exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) && (exerciseCompleted || testCompleted)) {
                    Intent result = new Intent();
                    result.putExtra("reps", reps);
                    result.putExtra("testCompleted", testCompleted);
                    result.putExtra("exerciseLevel", exerciseLevel);
                    result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                }

                // The isometric exercise and the test were not completed, so the user can try again with the new set time
                if (!exerciseCompleted && !testCompleted && (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric"))) {
                    pauseTimer(PAUSE);
                }

                // The dynamic exercise and the test were not completed, so the user can try again with the new set time
                if (!exerciseCompleted && !testCompleted && (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic"))) {
                    repsDisplay.setText(String.valueOf(reps));
                    pauseTimer(PAUSE);
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }


}





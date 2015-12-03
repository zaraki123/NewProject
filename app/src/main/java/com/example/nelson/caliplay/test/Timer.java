package com.example.nelson.caliplay.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.CircularSeekBar;
import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 01/12/2015.
 */

public class Timer extends AppCompatActivity {
    private static final int PAUSE = 30000;
    private TextView timerDisplay;
    private Color color;
    private CircularSeekBar seekBar;
    private int sets = 0;
    private int secs = 0;
    private CountDownTimer timer1;
    private int msecs = 10000;
    private MediaPlayer clockTicking, applause, racestart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.strength_test);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();
        clockTicking = MediaPlayer.create(this, R.raw.clock_ticking);
        applause = MediaPlayer.create(this, R.raw.applause);
        racestart1 = MediaPlayer.create(this, R.raw.race_start1);
    }

    private void openTimer(int msecs) {
        secs = 0;
        seekBar.setProgress(secs);
        seekBar.setMax(msecs / 1000);
        seekBar.setCircleProgressColor(color.parseColor("#ffa600"));
        seekBar.setPointerColor(color.parseColor("#ff3700"));
        seekBar.setPointerHaloColor(color.parseColor("#ae0300"));
        timer1 = new CountDownTimer(msecs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + millisUntilFinished / 1000);
                seekBar.setProgress(secs);
                clockTicking.start();
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);
                timerDisplay.setText("0");
                applause.start();

                    Intent result = new Intent(getApplicationContext(), TestResult.class);
                    result.putExtra("result", secs);
                    if (result.resolveActivity(getPackageManager()) != null) {
                        seekBar.setProgress(0);
                        startActivityForResult(result, 1);
                    }
                }

        };
    }

    public void start(View view) {
        openTimer(msecs);
        timer1.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                msecs = data.getIntExtra("milliseconds", 1);
                openTimer2();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }

    }

    private void openTimer2() {
        secs = 0;
        seekBar.setProgress(secs);
        seekBar.setMax(PAUSE / 1000);
        seekBar.setCircleProgressColor(color.parseColor("#00d4ff"));
        seekBar.setPointerColor(color.parseColor("#0088ff"));
        seekBar.setPointerHaloColor(color.parseColor("#000dff"));
        timer1 = new CountDownTimer(PAUSE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + millisUntilFinished / 1000);
                seekBar.setProgress(secs);
                if (secs <= 29 && secs >= 28) {
                    racestart1.start();
                } else {
                    clockTicking.start();
                }
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);
                timerDisplay.setText("0");
                applause.start();
                openTimer(msecs);
                timer1.start();
            }

        }.start();
    }
}


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

public class StrengthTest extends AppCompatActivity {
    private TextView timerDisplay;
    private CircularSeekBar seekBar;
    private int secs = 0;
    private CountDownTimer timer;
    private int msecs = 10000;
    private MediaPlayer clockTicking, applause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.strength_test);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();
        clockTicking = MediaPlayer.create(this, R.raw.clock_ticking);
        applause = MediaPlayer.create(this, R.raw.applause);
    }

    private void openTimer(int msecs) {
        secs = 0;
        seekBar.setProgress(secs);
        seekBar.setMax(msecs / 1000);
        timer = new CountDownTimer(msecs, 1000) {
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
        timer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                msecs = data.getIntExtra("milliseconds", 1);
                openTimer(30000);
                timer.start();
                openTimer(msecs);
                timer.start();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }

    }
}


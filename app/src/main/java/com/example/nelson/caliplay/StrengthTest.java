package com.example.nelson.caliplay;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Zaraki on 01/12/2015.
 */
public class StrengthTest extends AppCompatActivity {

    private TextView timerDisplay;
    private CircularSeekBar seekBar;
    private CountDownTimer timer;
    private int secs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.strength_test);
        seekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        timerDisplay = (TextView) findViewById(R.id.timer);
        timerDisplay.bringToFront();
        seekBar.setProgress(secs);
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs++;
                timerDisplay.setText("" + millisUntilFinished/1000);
                seekBar.setProgress(secs);
            }

            @Override
            public void onFinish() {
                secs++;
                seekBar.setProgress(secs);

            }
        };


    }

    public void start(View view) {
        timer.start();
    }

}

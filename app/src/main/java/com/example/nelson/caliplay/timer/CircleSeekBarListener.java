package com.example.nelson.caliplay.timer;

import android.graphics.Color;

import com.example.nelson.caliplay.utils.CircularSeekBar;

/**
 * Created by Zaraki on 11/12/2015.
 */
public class CircleSeekBarListener implements CircularSeekBar.OnCircularSeekBarChangeListener {


    @Override
    public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean fromUser) {

        if ((seekBar.getMax() - progress) <= 3) {
            seekBar.setCircleProgressColor(Color.parseColor("#ffffff"));
        } else if (seekBar.getPointerColor() == Color.parseColor("#4c9700")) {
            seekBar.setCircleProgressColor(Color.parseColor("#80ff00"));
        } else if (seekBar.getPointerColor() == Color.parseColor("#ff3700")) {
            seekBar.setCircleProgressColor(Color.parseColor("#ffa600"));
        } else if (seekBar.getPointerColor() == Color.parseColor("#0088ff")) {
            seekBar.setCircleProgressColor(Color.parseColor("#00d4ff"));
        }
    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {


    }

}

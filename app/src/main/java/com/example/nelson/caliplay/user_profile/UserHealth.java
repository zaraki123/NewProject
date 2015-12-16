package com.example.nelson.caliplay.user_profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nelson.caliplay.ListSports;
import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;
import com.example.nelson.caliplay.model.User;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class UserHealth extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private String lifeStyle = "";
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_health);
        nextButton = (ImageButton)findViewById(R.id.userHealthNextButton);
        nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_not_clickable));
    }


    public void onRadioLifeStyle(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sedentary:
                if (checked) {
                    lifeStyle = "sedentary";
                    nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_button));
                }
                break;
            case R.id.active:
                if (checked) {
                    lifeStyle = "active";
                    nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_button));
                }
                break;
            case R.id.athletic:
                if (checked) {
                    lifeStyle = "athlete";
                    nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_button));
                }
                break;
            case R.id.veryAthletic:
                if (checked) {
                    lifeStyle = "competitive";
                    nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_button));
                }
                break;
        }
    }

    public void userHealthNextButton(View view) {
        nextActivity();

    }

    private void nextActivity() {
        if (lifeStyle.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Choose a lifestyle", Toast.LENGTH_LONG).show();
        } else {
            Intent sport = new Intent(this, UserSport.class);
            if (sport.resolveActivity(getPackageManager()) != null) {
                startActivity(sport);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(distanceX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
            } else {
                // onTouch(e);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void onSwipeRight() {

    }

    public void onSwipeLeft() {
        nextActivity();
    }


}






package com.example.nelson.caliplay.user_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class UserHealth extends AppCompatActivity {


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
                overridePendingTransition(R.anim.slide_in_next, R.anim.slide_out_next);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent me) {

        return gestureDetector.onTouchEvent(me);
    }

    GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
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
    };

    GestureDetector gestureDetector = new GestureDetector(null,
            simpleOnGestureListener);

    public void onSwipeRight() {
        this.finish();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    public void onSwipeLeft() {
        nextActivity();
    }


}






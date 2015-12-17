package com.example.nelson.caliplay.user_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 15/12/2015.
 */
public class UserSport extends AppCompatActivity {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private ImageButton sportYesPresent, sportYesPast, sportNoPresent, sportNoPast, teamSport, individualSport, nextActivity;

    private int sportPresent = -1, sportPast = -1, questionSportPresentAnswered = 0, questionSportPastAnswered = 0, questionSportKind = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sport);
        sportYesPresent = (ImageButton) findViewById(R.id.sportPresentYes);
        sportNoPresent = (ImageButton) findViewById(R.id.sportPresentNo);
        sportYesPast = (ImageButton) findViewById(R.id.sportPastYes);
        sportNoPast = (ImageButton) findViewById(R.id.sportPastNo);
        teamSport = (ImageButton) findViewById(R.id.team_sport);
        individualSport = (ImageButton) findViewById(R.id.individual_sport);
        teamSport.setBackground(ContextCompat.getDrawable(this, R.drawable.team_sport_not_clickable));
        individualSport.setBackground(ContextCompat.getDrawable(this, R.drawable.individual_sport_not_clickable));
        teamSport.setClickable(false);
        individualSport.setClickable(false);
        nextActivity = (ImageButton) findViewById(R.id.userSportNextButton);
        nextActivity.setBackground(ContextCompat.getDrawable(this, R.drawable.next_not_clickable));
        nextActivity.setClickable(false);
    }


    public void sportPresentYes(View view) {
        sportYesPresent.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_pressed));
        sportNoPresent.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_no));
        sportPresent = 1;
        questionSportPresentAnswered = 1;
        kindSportEnableDisable();
        activateNextButton();
    }

    public void sportPresentNo(View view) {
        sportYesPresent.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_yes));
        sportNoPresent.setBackground(ContextCompat.getDrawable(this, R.drawable.relax_pressed));
        sportPresent = 0;
        questionSportPresentAnswered = 1;
        kindSportEnableDisable();
        activateNextButton();
    }

    public void sportPastYes(View view) {
        sportYesPast.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_pressed));
        sportNoPast.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_no));
        sportPast = 1;
        questionSportPastAnswered = 1;
        kindSportEnableDisable();
        activateNextButton();
    }

    public void sportPastNo(View view) {
        sportYesPast.setBackground(ContextCompat.getDrawable(this, R.drawable.sport_yes));
        sportNoPast.setBackground(ContextCompat.getDrawable(this, R.drawable.relax_pressed));
        sportPast = 0;
        questionSportPastAnswered = 1;
        kindSportEnableDisable();
        activateNextButton();
    }

    public void teamSport(View view) {
        teamSport.setBackground(ContextCompat.getDrawable(this, R.drawable.team_sport_pressed));
        individualSport.setBackground(ContextCompat.getDrawable(this, R.drawable.individual_sport));
        questionSportKind = 1;
        activateNextButton();

    }

    public void individualSport(View view) {
        teamSport.setBackground(ContextCompat.getDrawable(this, R.drawable.team_sport));
        individualSport.setBackground(ContextCompat.getDrawable(this, R.drawable.individual_sport_pressed));
        questionSportKind = 1;
        activateNextButton();
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

    private void onSwipeRight() {
        this.finish();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    private void onSwipeLeft() {
        user_sport_nextActivity();
        this.finish();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    private void kindSportEnableDisable() {

        if (sportPresent == 1 || sportPast == 1) {
            teamSport.setBackground(ContextCompat.getDrawable(this, R.drawable.team_sport));
            individualSport.setBackground(ContextCompat.getDrawable(this, R.drawable.individual_sport));
            teamSport.setClickable(true);
            individualSport.setClickable(true);
        } else if (sportPresent == 0 || sportPast == 0) {
            teamSport.setBackground(ContextCompat.getDrawable(this, R.drawable.team_sport_not_clickable));
            individualSport.setBackground(ContextCompat.getDrawable(this, R.drawable.individual_sport_not_clickable));
            teamSport.setClickable(false);
            individualSport.setClickable(false);
        }
    }

    private void activateNextButton() {
        if ((sportPresent == 0 && sportPast == 0) ||
                ((sportPresent == 1 && sportPast == 1) && (questionSportKind == 1)) ||
                ((sportPresent == 1 && sportPast == 0) && (questionSportKind == 1)) ||
                ((sportPresent == 0 && sportPast == 1) && (questionSportKind == 1))) {
            nextActivity.setBackground(ContextCompat.getDrawable(this, R.drawable.next_button));
            nextActivity.setClickable(true);
        } else {
            nextActivity.setBackground(ContextCompat.getDrawable(this, R.drawable.next_not_clickable));
            nextActivity.setClickable(false);
        }
    }

    public void user_sport_nextActivity() {


        Intent userSchedule = new Intent(this, UserSchedule.class);
        if (userSchedule.resolveActivity(getPackageManager()) != null) {
            startActivity(userSchedule);
            overridePendingTransition(R.anim.slide_in_next, R.anim.slide_out_next);
        }
    }
}




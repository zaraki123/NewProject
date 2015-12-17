package com.example.nelson.caliplay.user_profile;

import android.app.Dialog;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.MyApplication;
import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class UserProfile extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private static EditText username;
    private TextView height, weight;
    private String sex = "";
    private String age = "";
    private int altezza = 0, peso = 0;
    private MyApplication app;
    private ImageButton heightPicker, weightPicker, femaleIcon, maleIcon, nextButton;
    private RadioGroup child_young, adult_old;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        app = (MyApplication) getApplication();
        username = (EditText) findViewById(R.id.usernameEdit);
        height = (TextView) findViewById(R.id.height_measurement);
        weight = (TextView) findViewById(R.id.weight_measurement);
        femaleIcon = (ImageButton) findViewById(R.id.femaleIcon);
        maleIcon = (ImageButton) findViewById(R.id.maleIcon);
        child_young = (RadioGroup) findViewById(R.id.radioFirstRow);
        adult_old = (RadioGroup) findViewById(R.id.radioSecondRow);

        heightPicker = (ImageButton) findViewById(R.id.heightPicker);
        weightPicker = (ImageButton) findViewById(R.id.weightPicker);

        nextButton = (ImageButton) findViewById(R.id.userProfileNextButton);
        nextButton.setBackground(ContextCompat.getDrawable(this, R.drawable.next_not_clickable));

        heightPicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showHeight();
            }
        });
        weightPicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showWeight();
            }
        });


    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    public void userProfileFemaleIcon(View view) {
        sex = "female";
        femaleIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.female_pressed));
        femaleIcon.setClickable(false);
        maleIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.male_selector));
        maleIcon.setClickable(true);
        activateNextButton();

    }

    public void userProfileMaleIcon(View view) {
        sex = "male";
        maleIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.male_pressed));
        maleIcon.setClickable(false);
        femaleIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.female_selector));
        femaleIcon.setClickable(true);
        activateNextButton();
    }


    public void onRadioAge(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.child:
                if (checked) {
                    age = "<16";
                    adult_old.clearCheck();
                    activateNextButton();
                }
                break;
            case R.id.young:
                if (checked) {
                    age = "16-30";
                    adult_old.clearCheck();
                    activateNextButton();
                }
                break;
            case R.id.adult:
                if (checked) {
                    age = "31-50";
                    child_young.clearCheck();
                    activateNextButton();
                }
                break;
            case R.id.old:
                if (checked) {
                    age = ">50";
                    child_young.clearCheck();
                    activateNextButton();
                }
                break;

        }
    }

    public void showHeight() {

        final Dialog d = new Dialog(UserProfile.this);
        d.setTitle("Height");
        d.setContentView(R.layout.dialog);
        Button set = (Button) d.findViewById(R.id.set);
        Button cancel = (Button) d.findViewById(R.id.cancel);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(220);
        np.setMinValue(130);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText(String.valueOf(np.getValue()) + " cm");
                altezza = np.getValue();
                d.dismiss();
                activateNextButton();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
        activateNextButton();

    }

    public void showWeight() {

        final Dialog d = new Dialog(UserProfile.this);
        d.setTitle("Weight");
        d.setContentView(R.layout.dialog);
        Button set = (Button) d.findViewById(R.id.set);
        Button cancel = (Button) d.findViewById(R.id.cancel);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(130);
        np.setMinValue(40);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight.setText(String.valueOf(np.getValue()) + " kg");
                peso = np.getValue();
                d.dismiss();
                activateNextButton();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();

    }


    public void userProfileNextButton(View v) {

        // check the forms and the options before saving everything into the database
        nextActivity();

    }

    private void activateNextButton() {


        if (sex != "" && age != "" && altezza != 0 && peso != 0) {
            nextButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.next_button));
        }

    }




    public void onSwipeRight() {
        this.finish();
        overridePendingTransition(R.anim.slide_in_back, R.anim.slide_out_back);
    }

    public void onSwipeLeft() {
        nextActivity();
    }


    private void nextActivity() {
        if (app.getDataManager().findUser(username.getText().toString()) != null) {
            Toast.makeText(UserProfile.this, "Username not available", Toast.LENGTH_SHORT).show();
        } else if (username.getText().toString().isEmpty() || username.getText().toString().contains(" ")) {
            Toast.makeText(UserProfile.this, "Type a valid username without empty spaces", Toast.LENGTH_SHORT).show();
        } else if (sex.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose your sex", Toast.LENGTH_SHORT).show();
        } else if (age.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose your age", Toast.LENGTH_SHORT).show();
        } else if (altezza == 0) {
            Toast.makeText(UserProfile.this, "Choose your height", Toast.LENGTH_SHORT).show();
        } else if (peso == 0) {
            Toast.makeText(UserProfile.this, "Choose your weight", Toast.LENGTH_SHORT).show();
        } else {

            Intent lifeStyle = new Intent(this, UserHealth.class);
            if (lifeStyle.resolveActivity(getPackageManager()) != null) {
                startActivity(lifeStyle);
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


    @Override
    protected void onResume() {
        super.onResume();
    }
}




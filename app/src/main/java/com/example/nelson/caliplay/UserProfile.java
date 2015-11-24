package com.example.nelson.caliplay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nelson.caliplay.model.User;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class UserProfile extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static EditText height, weight, sportPast, sportPresent;
    private static EditText username;
    private String sex = "";
    private String age = "";
    private String lifeStyle = "";
    private String sportOfPresent = "";
    private String sportOfPast = "";
    private int altezza = 0, peso = 0;
    private User trainer = new User("gio", "maschio", "25", 0, 0, "", "", "");
    private User trainer2 = new User("nessuno", "nessuno", "25", 0, 0, "", "", "");
    private MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        app = (MyApplication) getApplication();
        username = (EditText) findViewById(R.id.username);
        height = (EditText) findViewById(R.id.height_measurement);
        weight = (EditText) findViewById(R.id.weight_measurement);
        sportPresent = (EditText) findViewById(R.id.sportPresent);
        sportPast = (EditText) findViewById(R.id.sportPast);

        Button pickerHeight = (Button) findViewById(R.id.heightPicker);
        Button pickerWeight = (Button) findViewById(R.id.weightPicker);
        Button sportPickerPresent = (Button) findViewById(R.id.sportPickerPresent);
        Button sportPickerPast = (Button) findViewById(R.id.sportPickerPast);

        pickerHeight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showHeight();
            }
        });
        pickerWeight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showWeight();
            }
        });
        sportPickerPresent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sportPresentPicker();
            }
        });
        sportPickerPast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sportPastPicker();
            }
        });
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    public void onRadioSexClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.male:
                if (checked) {
                    sex = "male";
                }
                break;
            case R.id.female:
                if (checked) {
                    sex = "female";
                }
                break;
        }
    }

    public void onRadioAge(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.child:
                if (checked) {
                    age = "<16";
                }
                break;
            case R.id.young:
                if (checked) {
                    age = "16-30";
                }
                break;
            case R.id.adult:
                if (checked) {
                    age = "31-50";
                }
                break;
            case R.id.old:
                if (checked) {
                    age = ">50";
                }
                break;

        }
    }

    public void showHeight() {

        final Dialog d = new Dialog(UserProfile.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button set = (Button) d.findViewById(R.id.set);
        Button cancel = (Button) d.findViewById(R.id.cancel);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(220);
        np.setMinValue(50);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText(String.valueOf(np.getValue()));
                altezza = np.getValue();
                d.dismiss();
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

    public void showWeight() {

        final Dialog d = new Dialog(UserProfile.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button set = (Button) d.findViewById(R.id.set);
        Button cancel = (Button) d.findViewById(R.id.cancel);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(130);
        np.setMinValue(10);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight.setText(String.valueOf(np.getValue()));
                peso = np.getValue();
                d.dismiss();
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

    // list sports to let user a sport of the present
    public void sportPresentPicker() {
        String sportType = "sportPresent";
        Intent sportPicker = new Intent(this, ListSports.class);
        sportPicker.putExtra("sportType", sportType);
        if (sportPicker.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sportPicker, 1);

        }


    }

    // list sports to let user a sport of the past
    public void sportPastPicker() {
        String sportType = "sportPast";
        Intent sportPicker = new Intent(this, ListSports.class);
        sportPicker.putExtra("sportType", sportType);
        if (sportPicker.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sportPicker, 1);

        }


    }

    // get back what user typed in the username form
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String sportType = data.getStringExtra("sportType");
                String selectedSport = data.getStringExtra("selectedSport");
                if (sportType.equals("sportPresent")) {
                    sportPresent.setText(selectedSport);
                    sportOfPresent = selectedSport;
                } else if (sportType.equals("sportPast")) {
                    sportPast.setText(selectedSport);
                    sportOfPast = selectedSport;
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Select a sport", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }

    public void onRadioLifeStyle(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sedentary:
                if (checked) {
                    lifeStyle = "sedentary";
                }
                break;
            case R.id.active:
                if (checked) {
                    lifeStyle = "active";
                }
                break;
            case R.id.athletic:
                if (checked) {
                    lifeStyle = "athlete";
                }
                break;
            case R.id.veryAthletic:
                if (checked) {
                    lifeStyle = "competitive";
                }
                break;
        }
    }


    public void next(View v) {

        // check the forms and the options before saving everything into the database

        if (app.getDataManager().findUser(username.getText().toString()) != null) {
            Toast.makeText(UserProfile.this, "Username not available", Toast.LENGTH_SHORT).show();
        } else if (username.getText().toString().matches("") || username.getText().toString().contains(" ")) {
            Toast.makeText(UserProfile.this, "Type a valid username without empty spaces", Toast.LENGTH_SHORT).show();
        } else if (sex.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose your sex", Toast.LENGTH_SHORT).show();
        } else if (age.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose your age", Toast.LENGTH_SHORT).show();
        } else if (altezza == 0) {
            Toast.makeText(UserProfile.this, "Choose your height", Toast.LENGTH_SHORT).show();
        } else if (peso == 0) {
            Toast.makeText(UserProfile.this, "Choose your weight", Toast.LENGTH_SHORT).show();
        } else if (lifeStyle.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose your lifeStyle", Toast.LENGTH_SHORT).show();
        } else if (sportOfPresent.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose the sport you do today", Toast.LENGTH_SHORT).show();
        } else if (sportOfPast.isEmpty()) {
            Toast.makeText(UserProfile.this, "Choose the sport you did in the past", Toast.LENGTH_SHORT).show();
        } else {
            trainer.setUsername(username.getText().toString());
            trainer.setSex(sex);
            trainer.setAge(age);
            trainer.setHeight(altezza);
            trainer.setWeight(peso);
            trainer.setLifeStyle(lifeStyle);
            trainer.setSportPresent(sportOfPresent);
            trainer.setSportPast(sportOfPast);

            app.getDataManager().saveUser(trainer);
            trainer2 = app.getDataManager().findUser("Giovanni");
            if (trainer2 != null) {
                System.out.println(trainer2.getUsername());
                System.out.println(trainer2.getSex());
                System.out.println(trainer2.getAge());
                System.out.println(trainer2.getHeight());
                System.out.println(trainer2.getWeight());
                System.out.println(trainer2.getSportPresent());
            }

            Intent print = new Intent(this, UserPrint.class);
            if (print.resolveActivity(getPackageManager()) != null)
                startActivity(print);
        }
    }
}




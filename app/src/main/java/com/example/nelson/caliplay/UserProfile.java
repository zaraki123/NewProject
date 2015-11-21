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
import android.widget.Toast;

import com.example.nelson.caliplay.model.User;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class UserProfile extends AppCompatActivity implements NumberPicker.OnValueChangeListener {


    private static EditText height, weight, sportPast, sportPresent = null;
    private static EditText username;
    private String user_name;
    private User trainer = new User("gio", "maschio");
    private User trainer2 = new User("nessuno", "nessuno");
    public MyApplication app;


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


    public void sportPresentPicker() {
        String sportType = "sportPresent";
        Intent sportPicker = new Intent(this, ListSports.class);
        sportPicker.putExtra("sportType", sportType);
        if (sportPicker.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sportPicker, 1);

        }


    }

    public void sportPastPicker() {
        String sportType = "sportPast";
        Intent sportPicker = new Intent(this, ListSports.class);
        sportPicker.putExtra("sportType", sportType);
        if (sportPicker.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sportPicker, 1);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String sportType = data.getStringExtra("sportType");
                String selectedSport = data.getStringExtra("selectedSport");
                if (sportType.equals("sportPresent")) {
                    sportPresent.setText(selectedSport);
                } else if (sportType.equals("sportPast")) {
                    sportPast.setText(selectedSport);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Select a sport", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }

    public void next(View v) {
        user_name = username.getText().toString();
        String sex = "Maschio";
        trainer.setUsername(user_name);
        trainer.setSex(sex);
        app.getDataManager().saveUser(trainer);
        trainer2 = app.getDataManager().findUser("hghgki");
        if (trainer2 != null) {
            System.out.println(trainer2.getUsername().toString());
        }

    }

}

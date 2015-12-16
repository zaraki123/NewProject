package com.example.nelson.caliplay.user_profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelson.caliplay.ListSports;
import com.example.nelson.caliplay.R;

/**
 * Created by Zaraki on 15/12/2015.
 */
public class UserSport extends AppCompatActivity {

    private TextView sportPresent, sportPast;
    private String sportOfPresent, sportOfPast, sportType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_sport);


        sportPresent = (TextView) findViewById(R.id.sportPresent);
        sportPast = (TextView) findViewById(R.id.sportPast);


    }

    // list sports to let user a sport of the present
    public void sportPresentPicker(View view) {
        sportType = "sportPresent";
        Intent sportPicker = new Intent(this, ListSports.class);
        sportPicker.putExtra("sportType", sportType);
        if (sportPicker.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sportPicker, 1);

        }


    }

    // list sports to let user a sport of the past
    public void sportPastPicker(View view) {
        sportType = "sportPast";
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
}

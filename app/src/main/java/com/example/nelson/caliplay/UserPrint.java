package com.example.nelson.caliplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Zaraki on 24/11/2015.
 */

public class UserPrint extends AppCompatActivity {

    private TextView username, sex, sportPresent, lifeStyle;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_print);
        app = (MyApplication) getApplication();

        username = (TextView) findViewById(R.id.usernamePrint);
        sex = (TextView) findViewById(R.id.sexPrint);
        sportPresent = (TextView) findViewById(R.id.sportPresentPrint);
        lifeStyle = (TextView) findViewById(R.id.lifeStylePrint);

        username.setText(app.getDataManagerUser().findUser("Giovanni").getUsername());
        sex.setText(app.getDataManagerUser().findUser("Giovanni").getSex());
        sportPresent.setText(app.getDataManagerUser().findUser("Giovanni").getSportPresent());
        lifeStyle.setText(app.getDataManagerUser().findUser("Giovanni").getLifeStyle());
    }


}

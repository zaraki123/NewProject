package com.example.nelson.caliplay;

import android.app.ActionBar;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.VideoView;

/**
 * Created by Zaraki on 03/12/2015.
 */
public class ExerciseOverview extends AppCompatActivity {

    private MediaController videoPlayer;
    private VideoView video;
    private boolean playingTutorial = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

package com.example.nelson.caliplay;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
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
        setContentView(R.layout.exercise_overview);


        video = (VideoView)findViewById(R.id.video);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_example;
        video.setVideoURI(Uri.parse(path));
        videoPlayer = new MediaController(ExerciseOverview.this);
        videoPlayer.setAnchorView(video);
        videoPlayer.setMediaPlayer(video);
        video.setMediaController(videoPlayer);
        video.requestFocus();
        video.start();

    }
}

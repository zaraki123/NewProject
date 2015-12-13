package com.example.nelson.caliplay;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.timer.ReadyTimer;
import com.example.nelson.caliplay.utils.Constants;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

/**
 * Created by Zaraki on 05/12/2015.
 */
public class YouTube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private int secs = 0, reps = 0, exerciseLevel = 0;
    private ArrayList<Exercise> exerciseList;
    private TextView videoText;
    private ImageButton startExercise;
    private YouTubePlayer playerVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_view);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);
        Bundle extras = getIntent().getExtras();
        videoText = (TextView) findViewById(R.id.lookVideo);
        Typeface face = Typeface.createFromAsset(getAssets(), "font/droid_serif_bold.ttf");
        videoText.setTypeface(face);


        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            secs = extras.getInt("secs");
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            reps = extras.getInt("reps");
        }
        switch (exerciseList.get(exerciseLevel).getTypeOfMovement()) {
            case "Core":
                startExercise = (ImageButton)findViewById(R.id.startCoreTestReady);
                startExercise.setVisibility(View.INVISIBLE);

                break;

            case "Pulling":
                startExercise = (ImageButton)findViewById(R.id.startCoreTestReady);
                startExercise.setVisibility(View.INVISIBLE);

                break;

            case "Pushing":
                startExercise = (ImageButton)findViewById(R.id.startCoreTestReady);
                startExercise.setVisibility(View.INVISIBLE);

                break;

            case "Squat":
                startExercise = (ImageButton)findViewById(R.id.startCoreTestReady);
                startExercise.setVisibility(View.INVISIBLE);

                break;

            case "Calf":
                startExercise = (ImageButton)findViewById(R.id.startCoreTestReady);
                startExercise.setVisibility(View.INVISIBLE);

                break;

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        if (!wasRestored) {
            player.cueVideo(exerciseList.get(exerciseLevel).getVideoId());
        }
        player.loadVideo(exerciseList.get(exerciseLevel).getVideoId());
        playerVideo = player;

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();

        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();

        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {


        }

        @Override
        public void onPaused() {



        }

        @Override
        public void onStopped() {
            startExercise.setVisibility(View.VISIBLE);
            startExercise.setClickable(true);
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
            startExercise.setVisibility(View.VISIBLE);

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {
            startExercise.setVisibility(View.VISIBLE);

        }

        @Override
        public void onVideoEnded() {
            timer();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Constants.YOUTUBE_API_KEY, this);
        }


        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
            if (resultCode == RESULT_CANCELED) {
                System.out.println("Do nothing");
            }

        }

    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }


    public void startTimer(View view) {
       timer();

    }

    private void  timer() {
        Intent readyTimer = new Intent(YouTube.this, ReadyTimer.class);
        readyTimer.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        readyTimer.putExtra("exerciseLevel", exerciseLevel);
        if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Isometric")) {
            readyTimer.putExtra("secs", secs);
        } else if (exerciseList.get(exerciseLevel).getTypeOfContraction().equals("Dynamic")) {
            readyTimer.putExtra("reps", reps);
        } else {
            Toast.makeText(getApplicationContext(), "Qualcosa è andato storto", Toast.LENGTH_LONG).show();
        }
        if (readyTimer.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(readyTimer, 2);
        }
    }


}

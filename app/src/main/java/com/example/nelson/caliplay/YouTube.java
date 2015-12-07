package com.example.nelson.caliplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nelson.caliplay.model.Exercise;
import com.example.nelson.caliplay.test.Timer;
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
    private int msecs = 0, secs = 0, exerciseLevel = 0;
    private boolean testCompleted = false;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_view);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);
        Bundle extras = getIntent().getExtras();
        msecs = extras.getInt("msecs");
        exerciseList = extras.getParcelableArrayList("exerciseArrayList");
        exerciseLevel = extras.getInt("exerciseLevel");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(exerciseList.get(exerciseLevel).getVideoId());
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Constants.YOUTUBE_API_KEY, this);
        }
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                secs = data.getIntExtra("seconds", 1);
                exerciseList = data.getParcelableArrayListExtra("exerciseArrayList");
                Intent result = new Intent();
                result.putExtra("seconds", secs);
                result.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
                setResult(Activity.RESULT_OK, result);
                finish();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();//Write your code if there's no result
            }
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    public void startTimer(View view) {
        Intent startTimer = new Intent(this, Timer.class);
        startTimer.putParcelableArrayListExtra("exerciseArrayList", exerciseList);
        startTimer.putExtra("msecs", msecs);
        startTimer.putExtra("exerciseLevel", exerciseLevel);
        if (startTimer.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startTimer, 1);
        }

    }


}

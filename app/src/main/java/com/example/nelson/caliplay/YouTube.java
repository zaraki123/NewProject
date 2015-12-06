package com.example.nelson.caliplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nelson.caliplay.test.Timer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Zaraki on 05/12/2015.
 */
public class YouTube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private String videoID;
    private int msecs = 0, secs = 0;
    private boolean exerciseCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_view);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);
        Bundle extras = getIntent().getExtras();
        videoID = extras.getString("videoId");
        msecs = extras.getInt("msecs");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(videoID);
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
                exerciseCompleted = data.getBooleanExtra("exerciseCompleted", false);
                Intent result = new Intent();
                result.putExtra("seconds", secs);
                result.putExtra("exerciseCompleted", exerciseCompleted);
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
        startTimer.putExtra("msecs", msecs);
        if (startTimer.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(startTimer, 1);
        }

    }


}

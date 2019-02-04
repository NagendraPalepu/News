package com.own.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.own.news.R;
import com.own.news.response.NewsSourceResponse;
import com.own.news.utils.AppUtils;

import androidx.annotation.Nullable;

public class ChannelPlaybackActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    private NewsSourceResponse.Channels channels;


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.playback_view);

        if (getIntent ().getExtras () != null) {
            try {
                channels = (NewsSourceResponse.Channels) getIntent ().getExtras ().getSerializable ("Object");
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }

        youTubeView = findViewById (R.id.youtube_view);
        youTubeView.initialize (AppUtils.DEVELOPER_KEY, this);


    }


    @Override
    public void onInitializationSuccess (YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        if (!wasRestored) {

            player.loadVideo (channels.getCode()); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure (YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError ()) {
            errorReason.getErrorDialog (this, RECOVERY_REQUEST).show ();
        } else {
            String error = String.format (getString (R.string.player_error), errorReason.toString ());
            Toast.makeText (this, error, Toast.LENGTH_LONG).show ();
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider ().initialize (AppUtils.DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider () {
        return youTubeView;
    }
}

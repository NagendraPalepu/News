package com.own.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.own.news.R;
import com.own.news.network.API;
import com.own.news.network.RetrofitHelper;
import com.own.news.response.NewsSourceResponse;
import com.own.news.response.VideoDataResponse;
import com.own.news.utils.AppUtils;
import com.own.news.utils.CircleImageView;
import com.own.news.utils.ImageUtils;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelPlaybackActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    private NewsSourceResponse.Channels channels;
    private CircleImageView logo;
    private TextView title, channelChange;
    private ImageView upImageView, downImageView;


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

        logo = findViewById (R.id.logo);
        title = findViewById (R.id.title);
        channelChange = findViewById (R.id.channelChange);
        upImageView = findViewById (R.id.upImageView);
        downImageView = findViewById (R.id.downImageView);

        title.setTypeface (AppUtils.getOpenSansSemiBold (this));
        channelChange.setTypeface (AppUtils.getOpenSansRegular (this));


        youTubeView = findViewById (R.id.youtube_view);
        youTubeView.initialize (AppUtils.DEVELOPER_KEY, this);

        getUrlData ();
    }


    @Override
    public void onInitializationSuccess (YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        if (!wasRestored) {

            player.loadVideo (channels.getCode ());
            player.setPlayerStyle (YouTubePlayer.PlayerStyle.DEFAULT);
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

    private void getUrlData () {
        if (channels == null) {
            return;
        }


        String parentUrl = API.APP_YOUTUBE_URL_REST;
        Call<VideoDataResponse> newsResponseCall = RetrofitHelper.getCommonApi (this, parentUrl).getVideoData ("https://www.youtube.com/watch?v=" + channels.getCode (), "json");

        newsResponseCall.enqueue (new Callback<VideoDataResponse> () {
            @Override
            public void onResponse (@NonNull Call<VideoDataResponse> call, @NonNull Response<VideoDataResponse> response) {
                VideoDataResponse videoDataResponse = response.body ();
                if (videoDataResponse != null) {
                    title.setText (videoDataResponse.getTitle ());
                    ImageUtils.loadImage (videoDataResponse.getThumbnail_url (), logo, ImageUtils.getRequestOptions (R.mipmap.ic_launcher));
                }
            }

            @Override
            public void onFailure (@NonNull Call<VideoDataResponse> call, @NonNull Throwable t) {

            }
        });
    }
}

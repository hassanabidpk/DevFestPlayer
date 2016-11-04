package com.gdgkr.devfestplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity implements ExoPlayer.EventListener,
        PlaybackControlView.VisibilityListener {

    private static final String TAG = PlayerActivity.class.getSimpleName();
    private static final String MP4_URL = "http://html5demos.com/assets/dizzy.mp4";
    private static final String VP9_NO_AUDIO_URL = "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-vp9-360.webm";

    SimpleExoPlayer player;
    SimpleExoPlayerView playerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        createExoPlayerInstance();

        playerView = (SimpleExoPlayerView) findViewById(R.id.player_view);
        playerView.setPlayer(player);
        playerView.setControllerVisibilityListener(this);
        playerView.requestFocus();

        preparePlayer();
    }


    private void createExoPlayerInstance() {

        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();

         player =
                ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

    }

    private void preparePlayer() {

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"), bandwidthMeter);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        Uri mp4VideoUri =  Uri.parse(MP4_URL)
                .buildUpon().build();
        MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
                dataSourceFactory, extractorsFactory, null, null);


        player.prepare(videoSource);

    }


    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }


    @Override
    public void onVisibilityChange(int visibility) {

    }
}

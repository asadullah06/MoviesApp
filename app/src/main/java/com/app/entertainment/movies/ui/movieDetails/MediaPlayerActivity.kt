package com.app.entertainment.movies.ui.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.NonNull
import com.app.entertainment.movies.databinding.MediaPlayerActivityBinding
import com.app.entertainment.movies.ui.BaseActivity
import com.app.entertainment.movies.utils.VIDEO_URL
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class MediaPlayerActivity : BaseActivity() {
    val TAG = "MediaPlayerActivity"
    lateinit var binding: MediaPlayerActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MediaPlayerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = intent.getStringExtra(VIDEO_URL)
                youTubePlayer.loadVideo(videoId, 0f)
            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                super.onVideoDuration(youTubePlayer, duration)
                Log.d(TAG, duration.toString())
            }
        })
        /*val videoURL = intent.getStringExtra(VIDEO_URL)
        val player = SimpleExoPlayer.Builder(this).build()
        // Build the media item.
        val mediaItem = MediaItem.fromUri(videoURL)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        binding.videoView.player = player

        // Start the playback.
        player.play()*/
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}
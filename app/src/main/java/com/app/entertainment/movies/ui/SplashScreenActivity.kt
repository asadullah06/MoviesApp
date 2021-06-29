package com.app.entertainment.movies.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.app.entertainment.movies.databinding.SplashScreenBinding
import com.app.entertainment.movies.ui.moviesListing.MoviesListingActivity
import com.app.entertainment.movies.utils.SPLASH_SCREEN_TIMER

class SplashScreenActivity : Activity() {
    lateinit var binding: SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread {
            Thread.sleep(SPLASH_SCREEN_TIMER)
            this.runOnUiThread {
                this.finish()
                startMoviesListingActivity()
            }
        }.start()
    }

    private fun startMoviesListingActivity() {
        val intent = Intent(this, MoviesListingActivity::class.java)
        startActivity(intent)
    }
}
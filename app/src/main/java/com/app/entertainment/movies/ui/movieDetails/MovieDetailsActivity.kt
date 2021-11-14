package com.app.entertainment.movies.ui.movieDetails

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.app.entertainment.movies.R
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.databinding.MovieDetailsBinding
import com.app.entertainment.movies.ui.BaseActivity
import com.app.entertainment.movies.utils.MOVIE_ID
import com.app.entertainment.movies.utils.VIDEO_TYPE_TRAILER
import com.app.entertainment.movies.utils.VIDEO_URL
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {
    lateinit var binding: MovieDetailsBinding
    val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    lateinit var imageViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Movie Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieDetailsViewModel.getMovieMetadata(intent.getIntExtra(MOVIE_ID, -1))

        collectMovieDetailsEvents()
        collectMovieImagesEvents()
        collectMovieVideosEvents()


        setClickOnPlayTrailerButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setClickOnPlayTrailerButton() {
        binding.watchTrailerButton.setOnClickListener {
            playTrailer(binding.watchTrailerButton.tag.toString())
        }
    }

    private fun playTrailer(videoKey: String) {
        val intent = Intent(this, MediaPlayerActivity::class.java)
        intent.putExtra(VIDEO_URL, videoKey)
        startActivity(intent)
    }

    private fun setAdapterOnViewpager(imagesArray: Array<String?>) {
        imageViewPagerAdapter = ViewPagerAdapter(this, imagesArray)
        binding.imagesViewpager.adapter = imageViewPagerAdapter
        populateDots()
    }

    private fun populateDots() {
        val dotscount = imageViewPagerAdapter.count
        val dots: Array<ImageView?> = arrayOfNulls(dotscount)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.non_active_dot))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.SliderDots.addView(dots[i], params)
        }
        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot))


        binding.imagesViewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@MovieDetailsActivity,
                            R.drawable.non_active_dot
                        )
                    )
                }
                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun collectMovieDetailsEvents() {
        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.movieDetailsStateFlow.collect { event ->

                when (event) {
                    is MovieDetailsViewModel.MovieDetailsEvents.Loading -> {
                        showProgressDialog()
                    }
                    is MovieDetailsViewModel.MovieDetailsEvents.Success -> {
                        hideProgressDialog()
                        populateMovieDetails(event.movieDetails)
                    }
                    is MovieDetailsViewModel.MovieDetailsEvents.Failure -> {
                        hideProgressDialog()
                        Snackbar.make(binding.root, event.errorText, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun collectMovieImagesEvents() {
        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.movieImagesStateFlow.collect { event ->

                when (event) {
                    is MovieDetailsViewModel.MovieImagesEvents.Loading -> {
                        showProgressDialog()
                    }
                    is MovieDetailsViewModel.MovieImagesEvents.Success -> {
                        hideProgressDialog()
                        populateMovieImages(event.movieImages)
                    }
                    is MovieDetailsViewModel.MovieImagesEvents.Failure -> {
                        hideProgressDialog()
                        Snackbar.make(binding.root, event.errorText, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun collectMovieVideosEvents() {
        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.movieVideosStateFlow.collect { event ->

                when (event) {
                    is MovieDetailsViewModel.MovieVideosEvents.Loading -> {
                        showProgressDialog()
                    }
                    is MovieDetailsViewModel.MovieVideosEvents.Success -> {
                        hideProgressDialog()

                        val videos = event.movieVideos.getVideoResults()
                        for (i in videos.indices) {
                            if (videos[i].videoType.equals(VIDEO_TYPE_TRAILER)) {
                                binding.watchTrailerButton.isEnabled = true
                                binding.watchTrailerButton.tag = videos[i].videoKey
                            }
                        }
                    }
                    is MovieDetailsViewModel.MovieVideosEvents.Failure -> {
                        hideProgressDialog()
                        Snackbar.make(binding.root, event.errorText, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun populateMovieDetails(it: MovieDetailsModel) {
        binding.movieTitleTextview.text = it.movieName
        binding.overviewTextview.text = it.overview
        binding.releaseDateTextview.text = it.releaseDate
        val genresModels = it.genresModels
        val genresString = ArrayList<String>()
        for (i in it.genresModels.indices) {
            genresString.add(genresModels[i].genresName)
        }
        if (genresString.isNotEmpty()) {
            binding.genresDetailsTextview.text =
                genresString.toString().replace("[", "").replace("]", "")
        }

        binding.ratingTextview.text = it.avgVote.toString()
    }

    private fun populateMovieImages(it: MovieImagesModel) {
        if (it.backdrops.isNotEmpty()) {
            val backdropArray = it.backdrops
            val totalImages: Int = if (backdropArray.size in 1..4) {
                backdropArray.size
            } else {
                5
            }
            val imagesArray: Array<String?> = arrayOfNulls(totalImages)

            for (i in 0 until totalImages) {
                imagesArray[i] = backdropArray[i].filePath
            }
            if (imagesArray.isNotEmpty()) {
                setAdapterOnViewpager(imagesArray)
            }
        }
    }
}
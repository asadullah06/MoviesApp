package com.app.entertainment.movies.ui.moviesListing

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.entertainment.movies.databinding.MoviesListingActivityBinding
import com.app.entertainment.movies.ui.BaseActivity
import com.app.entertainment.movies.ui.movieDetails.MovieDetailsActivity
import com.app.entertainment.movies.utils.MOVIE_ID
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MoviesListingActivity : BaseActivity() {
    lateinit var binding: MoviesListingActivityBinding
    private val moviesListingViewModel: MoviesListingViewModel by viewModels()
    lateinit var moviesListingAdapter: MoviesListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviesListingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesListingAdapter = MoviesListingAdapter(this, object : OnClickListener {
            override fun onItemClicked(position: Int) {
                callMovieDetailsScreen(moviesListingAdapter.moviesList[position].movieId)
            }
        })
        binding.moviesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerview.adapter = moviesListingAdapter

        collectResponseEvents()
    }

    private fun callMovieDetailsScreen(movieId: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    private fun collectResponseEvents() {
        lifecycleScope.launchWhenStarted {
            moviesListingViewModel.moviesListStateFlow.collect { event ->

                when (event) {
                    is MoviesListingViewModel.MoviesListingEvents.Loading -> {
                        startShimmerAnimation()
                    }
                    is MoviesListingViewModel.MoviesListingEvents.Success -> {
                        stopShimmerAnimation()
                        if (moviesListingAdapter.moviesList.isEmpty())
                            moviesListingAdapter.moviesList =
                                event.moviesObject.getUpcomingMovies().toCollection(ArrayList())
                        else
                            moviesListingAdapter.moviesList.addAll(
                                event.moviesObject.getUpcomingMovies().toCollection(
                                    ArrayList()
                                )
                            )
                        moviesListingAdapter.notifyDataSetChanged()

                    }
                    is MoviesListingViewModel.MoviesListingEvents.Failure -> {
                        stopShimmerAnimation()
                        Snackbar.make(binding.root, event.errorText, Snackbar.LENGTH_LONG).show()
                    }

                }

            }

        }
    }

    private fun startShimmerAnimation() {
        binding.shimmerViewContainer.startShimmerAnimation()
        binding.shimmerViewContainer.isVisible = true
    }

    private fun stopShimmerAnimation() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        binding.shimmerViewContainer.isVisible = false
    }
}
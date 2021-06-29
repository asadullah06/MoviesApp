package com.app.entertainment.movies.ui.moviesListing

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.databinding.MoviesListingActivityBinding
import com.app.entertainment.movies.ui.BaseActivity
import com.app.entertainment.movies.ui.movieDetails.MovieDetailsActivity
import com.app.entertainment.movies.utils.MOVIE_ID
import com.paginate.Paginate


class MoviesListingActivity : BaseActivity() {
    lateinit var binding: MoviesListingActivityBinding
    lateinit var moviesListingViewModel: MoviesListingViewModel
    lateinit var moviesListingAdapter: MoviesListingAdapter
    var loadingInProgress: Boolean = false
    lateinit var lastMoviesObject: UpcomingMoviesResponseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviesListingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesListingViewModel = MoviesListingViewModel()
        moviesListingAdapter = MoviesListingAdapter(this, object : OnClickListener {
            override fun onItemClicked(position: Int) {
                callMovieDetailsScreen(moviesListingAdapter.moviesList[position].movieId)
            }
        })
        binding.moviesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerview.adapter = moviesListingAdapter

        moviesListingViewModel.upcomingMoviesResponseModel.observe(this, {
            loadingInProgress =false
            lastMoviesObject = it
            if (moviesListingAdapter.moviesList.isEmpty())
                moviesListingAdapter.moviesList = it.upcomingMovies.toCollection(ArrayList())
            else
                moviesListingAdapter.moviesList.addAll(it.upcomingMovies.toCollection(ArrayList()))
            moviesListingAdapter.notifyDataSetChanged()
        })
    }

    private fun callMovieDetailsScreen(movieId: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }
}
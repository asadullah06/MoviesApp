package com.app.entertainment.movies.ui.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.repository.MoviesRepository
import com.app.entertainment.movies.utils.DispatcherProvider
import com.app.entertainment.movies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel
@Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val dispatcher: DispatcherProvider
)
    : ViewModel() {
    private var _movieDetailsStateFlow = MutableStateFlow<MovieDetailsEvents>(MovieDetailsEvents.Empty)
    val movieDetailsStateFlow = _movieDetailsStateFlow
    private var _movieImagesStateFlow = MutableStateFlow<MovieImagesEvents>(MovieImagesEvents.Empty)
    val movieImagesStateFlow = _movieImagesStateFlow
    private var _movieVideosStateFlow = MutableStateFlow<MovieVideosEvents>(MovieVideosEvents.Empty)
    val movieVideosStateFlow = _movieVideosStateFlow

    sealed class MovieDetailsEvents {
        object Loading : MovieDetailsEvents()
        data class Success(val movieDetails: MovieDetailsModel) : MovieDetailsEvents()
        data class Failure(val errorText: String) : MovieDetailsEvents()
        object Empty : MovieDetailsEvents()
    }

    sealed class MovieImagesEvents {
        object Loading : MovieImagesEvents()
        data class Success(val movieImages: MovieImagesModel) : MovieImagesEvents()
        data class Failure(val errorText: String) : MovieImagesEvents()
        object Empty : MovieImagesEvents()
    }

    sealed class MovieVideosEvents {
        object Loading : MovieVideosEvents()
        data class Success(val movieVideos: MovieVideosModel) : MovieVideosEvents()
        data class Failure(val errorText: String) : MovieVideosEvents()
        object Empty : MovieVideosEvents()
    }

    fun getMovieMetadata(movieId: Int) {
        getMovieDetails(movieId)
        getMovieImages(movieId)
        getMovieVideos(movieId)
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(dispatcher.io) {
            _movieDetailsStateFlow.value = MovieDetailsEvents.Loading
            when (val response = moviesRepository.getMovieDetails(movieId)) {
                is Resource.Success -> {
                    _movieDetailsStateFlow.value = MovieDetailsEvents.Success(response.data!!)
                }
                is Resource.Error -> {
                    _movieDetailsStateFlow.value = MovieDetailsEvents.Failure(response.message!!)
                }
            }
        }
    }

    private fun getMovieImages(movieId: Int) {
        viewModelScope.launch(dispatcher.io) {
            _movieImagesStateFlow.value = MovieImagesEvents.Loading
            when (val response = moviesRepository.getMovieImages(movieId)) {
                is Resource.Success -> {
                    _movieImagesStateFlow.value = MovieImagesEvents.Success(response.data!!)
                }
                is Resource.Error -> {
                    _movieImagesStateFlow.value = MovieImagesEvents.Failure(response.message!!)
                }
            }
        }
    }

    private fun getMovieVideos(movieId: Int) {
        viewModelScope.launch(dispatcher.io) {
            _movieVideosStateFlow.value = MovieVideosEvents.Loading
            when (val response = moviesRepository.getMovieVideos(movieId)) {
                is Resource.Success -> {
                    _movieVideosStateFlow.value = MovieVideosEvents.Success(response.data!!)
                }
                is Resource.Error -> {
                    _movieVideosStateFlow.value = MovieVideosEvents.Failure(response.message!!)
                }
            }
        }
    }
}
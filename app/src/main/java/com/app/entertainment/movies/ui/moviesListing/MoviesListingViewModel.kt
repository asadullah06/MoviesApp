package com.app.entertainment.movies.ui.moviesListing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.repository.MoviesRepository
import com.app.entertainment.movies.utils.DispatcherProvider
import com.app.entertainment.movies.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListingViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val dispatcher: DispatcherProvider
)
    : ViewModel() {

    private var _moviesListStateFlow =
        MutableStateFlow<MoviesListingEvents>(MoviesListingEvents.Empty)
    val moviesListStateFlow = _moviesListStateFlow

    sealed class MoviesListingEvents {
        object Loading : MoviesListingEvents()
        data class Success(val moviesObject: UpcomingMoviesResponseModel) : MoviesListingEvents()
        data class Failure(val errorText: String) : MoviesListingEvents()
        object Empty : MoviesListingEvents()
    }

    var totalLoadedPages = 1

    init {
        getMoviesList()
    }

    fun getMoviesList() {

        viewModelScope.launch(dispatcher.io) {
            _moviesListStateFlow.value = MoviesListingEvents.Loading
            when (val response = moviesRepository.getMoviesList(totalLoadedPages)) {
                is Resource.Success -> {
                    _moviesListStateFlow.value = MoviesListingEvents.Success(response.data!!)
                }
                is Resource.Error -> {
                    _moviesListStateFlow.value = MoviesListingEvents.Failure(response.message!!)
                }
            }
        }
    }
}

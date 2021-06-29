package com.app.entertainment.movies.ui.moviesListing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.repository.OnResponseSucceeded
import com.app.entertainment.movies.repository.RemoteMoviesRepo

class MoviesListingViewModel : ViewModel() {
    val remoteMoviesRepo = RemoteMoviesRepo()
    var upcomingMoviesResponseModel: MutableLiveData<UpcomingMoviesResponseModel> = MutableLiveData()
    var totalLoadedPages = 1

    init {
        remoteMoviesRepo.getMoviesList(totalLoadedPages, object : OnResponseSucceeded {
            override fun response(responseObject: Any?) {
                upcomingMoviesResponseModel.value = responseObject as UpcomingMoviesResponseModel
            }
        })
    }

}
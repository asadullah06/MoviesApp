package com.app.entertainment.movies.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.repository.OnResponseSucceeded
import com.app.entertainment.movies.repository.RemoteMoviesRepo

class MovieDetailsViewModel(movieId: Int) : ViewModel() {
    val remoteMoviesRepo = RemoteMoviesRepo()
    var movieDetailsModel: MutableLiveData<MovieDetailsModel> = MutableLiveData()
    var movieImagesModel: MutableLiveData<MovieImagesModel> = MutableLiveData()
    var movieVideosModel: MutableLiveData<MovieVideosModel> = MutableLiveData()


    init {
        remoteMoviesRepo.getMovieImages(movieId, object : OnResponseSucceeded {
            override fun response(responseObject: Any?) {
                movieImagesModel.value = responseObject as MovieImagesModel
            }
        })
        remoteMoviesRepo.getMovieVideos(movieId, object : OnResponseSucceeded {
            override fun response(responseObject: Any?) {
                movieVideosModel.value = responseObject as MovieVideosModel
            }
        })
        remoteMoviesRepo.getMovieDetails(movieId, object : OnResponseSucceeded {
            override fun response(responseObject: Any?) {
                movieDetailsModel.value = responseObject as MovieDetailsModel
            }
        })
    }
}
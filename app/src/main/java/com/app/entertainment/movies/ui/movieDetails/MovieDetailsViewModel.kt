package com.app.entertainment.movies.ui.movieDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.networkcalls.ApiClient
import com.app.entertainment.movies.networkcalls.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel(private val movieId: Int) : ViewModel() {
    val TAG = "MovieDetailsViewModel"
    var movieDetailsModel: MutableLiveData<MovieDetailsModel> = MutableLiveData()
    var movieImagesModel: MutableLiveData<MovieImagesModel> = MutableLiveData()
    var movieVideosModel: MutableLiveData<MovieVideosModel> = MutableLiveData()


    init {
        getMovieImages()
        getMovieVideos()
        getMovieDetails()
    }


    private fun getMovieDetails() {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieDetails(movieId)
        call.enqueue(object : Callback<MovieDetailsModel> {

            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                if (response.isSuccessful) {
                    movieDetailsModel.value = response.body()
                    Log.d(TAG, response.body().toString())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieDetailsModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

    fun getMovieImages() {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieImages(movieId)
        call.enqueue(object : Callback<MovieImagesModel> {

            override fun onResponse(
                call: Call<MovieImagesModel>,
                response: Response<MovieImagesModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    movieImagesModel.value = response.body()
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieImagesModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

    private fun getMovieVideos() {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieVideos(movieId)
        call.enqueue(object : Callback<MovieVideosModel> {

            override fun onResponse(
                call: Call<MovieVideosModel>,
                response: Response<MovieVideosModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    movieVideosModel.value = response.body()
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieVideosModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }
}
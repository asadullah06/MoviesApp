package com.app.entertainment.movies.repository

import android.util.Log
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.networkcalls.ApiClient
import com.app.entertainment.movies.networkcalls.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * centralized repository class that
 */
class RemoteMoviesRepo {
    val TAG = "RemoteMoviesRepo"


    /**
     * @param movieId to pass in the API request
     * @param onResponseSucceeded is interface instance to pass response back
     */
    fun getMovieDetails(movieId: Int, onResponseSucceeded: OnResponseSucceeded) {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieDetails(movieId)
        call.enqueue(object : Callback<MovieDetailsModel> {

            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                if (response.isSuccessful) {
                    onResponseSucceeded.response(response.body())
                    Log.d(TAG, response.body().toString())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieDetailsModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

    /**
     * @param movieId to pass in the API request
     * @param onResponseSucceeded is interface instance to pass response back
     */
    fun getMovieImages(movieId: Int, onResponseSucceeded: OnResponseSucceeded) {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieImages(movieId)
        call.enqueue(object : Callback<MovieImagesModel> {

            override fun onResponse(
                call: Call<MovieImagesModel>,
                response: Response<MovieImagesModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    onResponseSucceeded.response(response.body())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieImagesModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

    /**
     * @param movieId to pass in the API request
     * @param onResponseSucceeded is interface instance to pass response back
     */
    fun getMovieVideos(movieId: Int, onResponseSucceeded: OnResponseSucceeded) {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getMovieVideos(movieId)
        call.enqueue(object : Callback<MovieVideosModel> {

            override fun onResponse(
                call: Call<MovieVideosModel>,
                response: Response<MovieVideosModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    onResponseSucceeded.response(response.body())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<MovieVideosModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

    fun getMoviesList(pageToLoad: Int, onResponseSucceeded: OnResponseSucceeded) {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getupComingMoviesList(pageToLoad)
        call.enqueue(object : Callback<UpcomingMoviesResponseModel> {

            override fun onResponse(
                call: Call<UpcomingMoviesResponseModel>,
                response: Response<UpcomingMoviesResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    onResponseSucceeded.response(response.body())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<UpcomingMoviesResponseModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }

}
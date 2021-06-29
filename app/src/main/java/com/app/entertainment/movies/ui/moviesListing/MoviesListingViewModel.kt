package com.app.entertainment.movies.ui.moviesListing

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.networkcalls.ApiClient
import com.app.entertainment.movies.networkcalls.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListingViewModel : ViewModel() {
    val TAG = "MoviesListingViewModel"
    var upcomingMoviesResponseModel: MutableLiveData<UpcomingMoviesResponseModel> =
        MutableLiveData()
    var totalLoadedPages = 1

    init {
        getMoviesList(totalLoadedPages)
    }

    fun getMoviesList(pageToLoad: Int) {
        val apiService = ApiClient.getClient().create(ApiHelper::class.java)
        val call = apiService.getupComingMoviesList(pageToLoad)
        call.enqueue(object : Callback<UpcomingMoviesResponseModel> {

            override fun onResponse(
                call: Call<UpcomingMoviesResponseModel>,
                response: Response<UpcomingMoviesResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    upcomingMoviesResponseModel.value = response.body()!!
//                    moviesList.value = response.body()!!.upcomingMovies.toCollection(ArrayList())
                } else
                    Log.d(TAG, response.errorBody().toString())
            }

            override fun onFailure(call: Call<UpcomingMoviesResponseModel>, throwable: Throwable) {
                Log.e(TAG, "Error Message: " + throwable.localizedMessage)
            }
        })
    }
}
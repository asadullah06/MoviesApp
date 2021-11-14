package com.app.entertainment.movies.repository

import android.util.Log
import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.networkcalls.ApiHelper
import com.app.entertainment.movies.utils.Resource
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val api: ApiHelper
) : MoviesRepository {

    val TAG = "DefaultMoviesRepository"


    /**
     * @param movieId to pass in the API request
     */
    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsModel> {
        return try {
            val response = api.getMovieDetails(movieId)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Log.i(TAG, result.toString())
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    /**
     * @param movieId to pass in the API request
     */
    override suspend fun getMovieImages(movieId: Int): Resource<MovieImagesModel> {
        return try {
            val response = api.getMovieImages(movieId)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Log.i(TAG, result.toString())
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    /**
     * @param movieId to pass in the API request
     */
    override suspend fun getMovieVideos(movieId: Int): Resource<MovieVideosModel> {
        return try {
            val response = api.getMovieVideos(movieId)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Log.i(TAG, result.toString())
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }


    override suspend fun getMoviesList(
        pageToLoad: Int
    ): Resource<UpcomingMoviesResponseModel> {
        return try {
            val response = api.getupComingMoviesList(pageToLoad)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Log.i(TAG, result.toString())
                Resource.Success(result)
            } else {
                Log.e(TAG, response.errorBody().toString())
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
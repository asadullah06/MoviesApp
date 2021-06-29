package com.app.entertainment.movies.networkcalls

import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    @GET("/3/movie/upcoming")
    fun getupComingMoviesList(
        @Query("page") pageToLoad: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<UpcomingMoviesResponseModel>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieDetailsModel>

    @GET("/3/movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieImagesModel>

    @GET("/3/movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<MovieVideosModel>

}
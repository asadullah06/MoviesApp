package com.app.entertainment.movies.repository

import com.app.entertainment.movies.data.remote.MovieDetailsModel
import com.app.entertainment.movies.data.remote.MovieImagesModel
import com.app.entertainment.movies.data.remote.MovieVideosModel
import com.app.entertainment.movies.data.remote.UpcomingMoviesResponseModel
import com.app.entertainment.movies.utils.Resource

interface MoviesRepository {

    suspend fun getMoviesList(pageToLoad: Int): Resource<UpcomingMoviesResponseModel>
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsModel>
    suspend fun getMovieImages(movieId: Int): Resource<MovieImagesModel>
    suspend fun getMovieVideos(movieId: Int): Resource<MovieVideosModel>
}
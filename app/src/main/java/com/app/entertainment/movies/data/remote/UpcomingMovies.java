package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;


public class UpcomingMovies {

    @SerializedName("id")
    private final int movieId;
    @SerializedName("adult")
    private final boolean isAdult;
    @SerializedName("title")
    private final String movieName;
    @SerializedName("poster_path")
    private final String poster;
    @SerializedName("release_date")
    private final String releaseDate;


    UpcomingMovies(int movieId, String movieName, String poster, String releaseDate, boolean isAdult) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.poster = poster;
        this.releaseDate = releaseDate;
        this.isAdult = isAdult;
    }


    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getPoster() {
        return poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isAdult() {
        return isAdult;
    }
}

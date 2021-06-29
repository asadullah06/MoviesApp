package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class MovieDetailsModel {
    @SerializedName("overview")
    private String overview;
    @SerializedName("original_title")
    private String movieName;
    @SerializedName("genres")
    private GenresModel[] genresModels;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("popularity")
    private float moviePopularity;
    @SerializedName("vote_average")
    private float avgVote;

    public String getOverview() {
        return overview;
    }

    public String getMovieName() {
        return movieName;
    }

    public GenresModel[] getGenresModels() {
        return genresModels;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getMoviePopularity() {
        return moviePopularity;
    }

    public float getAvgVote() {
        return avgVote;
    }
}

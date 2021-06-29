package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class UpcomingMoviesResponseModel {
    @SerializedName("page")
    private int pageNumber;
    @SerializedName("results")
    private UpcomingMovies[] upcomingMovies;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_result")
    private int totalResult;


    public int getPageNumber() {
        return pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public UpcomingMovies[] getUpcomingMovies() {
        return upcomingMovies;
    }
}

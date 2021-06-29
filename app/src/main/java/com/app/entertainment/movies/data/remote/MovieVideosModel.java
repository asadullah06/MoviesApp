package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class MovieVideosModel {
    @SerializedName("id")
    private int videosId;
    @SerializedName("results")
    private VideoResults[] videoResults;

    public VideoResults[] getVideoResults() {
        return videoResults;
    }
}

package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class VideoResults {
    @SerializedName("key")
    private String videoKey;
    @SerializedName("type")
    private String videoType;

    public String getVideoKey() {
        return videoKey;
    }

    public String getVideoType() {
        return videoType;
    }
}

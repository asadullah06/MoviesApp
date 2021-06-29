package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class MovieImagesModel {
    @SerializedName("id")
    private int imagesId;

    @SerializedName("backdrops")
    private Backdrop[] backdrops;

    public int getImagesId() {
        return imagesId;
    }

    public Backdrop[] getBackdrops() {
        return backdrops;
    }
}

package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class GenresModel {
    @SerializedName("id")
    private int genresId;
    @SerializedName("name")
    private String genresName;

    public int getGenresId() {
        return genresId;
    }

    public String getGenresName() {
        return genresName;
    }
}

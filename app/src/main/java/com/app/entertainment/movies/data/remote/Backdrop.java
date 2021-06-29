package com.app.entertainment.movies.data.remote;

import com.google.gson.annotations.SerializedName;

public class Backdrop {
    @SerializedName("file_path")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}

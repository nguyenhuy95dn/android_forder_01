package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {
    @SerializedName("url")
    private String mUrl;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
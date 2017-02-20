package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("standard")
    private Standard mStandard;
    @SerializedName("thumbnail")
    private Thumbnail mThumbnail;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Standard getStandard() {
        return mStandard;
    }

    public void setStandard(Standard standard) {
        mStandard = standard;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
    }
}
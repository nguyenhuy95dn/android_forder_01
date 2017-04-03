package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Avatar implements Serializable {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(
        CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }
}

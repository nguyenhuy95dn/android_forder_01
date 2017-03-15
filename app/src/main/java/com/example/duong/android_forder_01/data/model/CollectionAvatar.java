package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CollectionAvatar implements Serializable {
    @SerializedName("avatar")
    private Avatar mAvatar;

    public CollectionAvatar() {
    }

    public CollectionAvatar(Avatar avatar) {
        mAvatar = avatar;
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar avatar) {
        mAvatar = avatar;
    }
}

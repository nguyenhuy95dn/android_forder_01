package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class CollectionAvatar {
    @SerializedName("avatar")
    private Avatar mAvatar;

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar avatar) {
        mAvatar = avatar;
    }
}

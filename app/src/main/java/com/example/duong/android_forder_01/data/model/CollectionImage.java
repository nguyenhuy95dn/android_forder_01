package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class CollectionImage {
    @SerializedName("image")
    private Image mImage;

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }
}

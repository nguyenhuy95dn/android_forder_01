package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CollectionImage implements Serializable {
    @SerializedName("image")
    private Image mImage;

    public CollectionImage(Image image) {
        mImage = image;
    }

    public CollectionImage(Cursor cursor) {
        mImage = new Image(cursor);
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }
}

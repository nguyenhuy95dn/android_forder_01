package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_DOMAIN;

public class Domain {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("user")
    private User mUser;

    public Domain() {
    }

    public Domain(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_DOMAIN));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}


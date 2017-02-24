package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("content")
    private User mUser;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("message")
    private String mMessage;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String reason) {
        mMessage = reason;
    }
}

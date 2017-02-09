package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("result")
    private boolean mResult;
    @SerializedName("error_message")
    private String mErrorMessage;
    @SerializedName("user")
    private User mUser;

    public boolean isResult() {
        return mResult;
    }

    public void setResult(boolean result) {
        mResult = result;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }
}

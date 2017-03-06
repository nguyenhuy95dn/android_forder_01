package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mId;
    @SerializedName("email")
    private String mUserName;
    @SerializedName("name")
    private String mFullName;
    @SerializedName("authentication_token")
    private String mAuthenticationToken;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getAuthenticationToken() {
        return mAuthenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        mAuthenticationToken = authenticationToken;
    }
}

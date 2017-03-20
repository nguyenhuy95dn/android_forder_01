package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mId;
    @SerializedName("email")
    private String mUserName;
    @SerializedName("name")
    private String mFullName;
    @SerializedName("member")
    private int mIsMember;
    @SerializedName("role")
    private int mRole;
    @SerializedName("owner")
    private int mOwner;
    @SerializedName("authentication_token")
    private String mAuthenticationToken;
    @SerializedName("avatar")
    private Avatar mAvatar;

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

    public int getIsMember() {
        return mIsMember;
    }

    public void setIsMember(int isMember) {
        mIsMember = isMember;
    }

    public int getRole() {
        return mRole;
    }

    public void setRole(int role) {
        mRole = role;
    }

    public int getOwner() {
        return mOwner;
    }

    public void setOwner(int owner) {
        mOwner = owner;
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar avatar) {
        mAvatar = avatar;
    }
}

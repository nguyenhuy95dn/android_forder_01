package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("id")
    private String mId;
    @SerializedName("user_name")
    private String mUserName;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("domains")
    private List<Domain> mListDomain;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public List<Domain> getListDomain() {
        return mListDomain;
    }

    public void setListDomain(
        List<Domain> listDomain) {
        mListDomain = listDomain;
    }
}

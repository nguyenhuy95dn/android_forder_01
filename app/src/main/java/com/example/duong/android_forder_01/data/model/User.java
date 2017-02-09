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

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public List<Domain> getListDomain() {
        return mListDomain;
    }

    public void setListDomain(
        List<Domain> listDomain) {
        mListDomain = listDomain;
    }
}

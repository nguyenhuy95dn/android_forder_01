package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shop {
    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("cover_image")
    private String mCoverImage;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("average_rating")
    private float mAverageRating;
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;
    @SerializedName("user")
    private User mUser;
    @SerializedName("domains")
    private List<Domain> mListDomain;
    @SerializedName("products")
    private List<Product> mListProduct;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getCoverImage() {
        return mCoverImage;
    }

    public void setCoverImage(String coverImage) {
        mCoverImage = coverImage;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public float getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(float averageRating) {
        mAverageRating = averageRating;
    }

    public String getTimeAutoReject() {
        return mTimeAutoReject;
    }

    public void setTimeAutoReject(String timeAutoReject) {
        mTimeAutoReject = timeAutoReject;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<Product> getListProduct() {
        return mListProduct;
    }

    public void setListProduct(
        List<Product> listProduct) {
        mListProduct = listProduct;
    }

    public List<Domain> getListDomain() {
        return mListDomain;
    }

    public void setListDomain(
        List<Domain> listDomain) {
        mListDomain = listDomain;
    }
}

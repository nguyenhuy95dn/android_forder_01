package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;

public class Shop implements Serializable {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;
    @SerializedName("average_rating")
    private float mAverageRating;
    @SerializedName("owner_id")
    private int mOwnerId;
    @SerializedName("user")
    private User mUser;
    @SerializedName("domains")
    private List<Domain> mListDomain;
    @SerializedName("products")
    private List<Product> mListProduct;
    @SerializedName("is_owner")
    private int mIsOwner;

    public Shop(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOP));
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }

    public float getAverageRating() {
        return mAverageRating;
    }

    public void setAverageRating(float averageRating) {
        mAverageRating = averageRating;
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

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Integer getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(Integer ownerId) {
        mOwnerId = ownerId;
    }

    public List<Domain> getListDomain() {
        return mListDomain;
    }

    public void setListDomain(
        List<Domain> listDomain) {
        mListDomain = listDomain;
    }

    public int getIsOwner() {
        return mIsOwner;
    }

    public void setIsOwner(int isOwner) {
        mIsOwner = isOwner;
    }
}

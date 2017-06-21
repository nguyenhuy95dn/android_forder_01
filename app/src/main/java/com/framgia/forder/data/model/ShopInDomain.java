package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 6/16/2017.
 */

public class ShopInDomain {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("avatar")
    private String mAvatar;
    @Expose
    @SerializedName("averate_rating")
    private float mAverateRating;
    @Expose
    @SerializedName("owner_id")
    private int mOwnerId;
    @Expose
    @SerializedName("owner_name")
    private String mOwnerName;
    @Expose
    @SerializedName("owner_avatar")
    private CollectionAvatar mCollectionAvatar;
    @Expose
    @SerializedName("shop_managers")
    private List<OwnerShop> mOwnerShops;
    @Expose
    @SerializedName("owner_email")
    private int mOwnerEmail;
    @Expose
    @SerializedName("total_products")
    private int mTotalProduct;
    private boolean mIsOwner;

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

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public float getAverateRating() {
        return mAverateRating;
    }

    public void setAverateRating(float averateRating) {
        mAverateRating = averateRating;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public void setOwnerName(String ownerName) {
        mOwnerName = ownerName;
    }

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }

    public List<OwnerShop> getOwnerShops() {
        return mOwnerShops;
    }

    public void setOwnerShops(List<OwnerShop> ownerShops) {
        mOwnerShops = ownerShops;
    }

    public int getOwnerEmail() {
        return mOwnerEmail;
    }

    public void setOwnerEmail(int ownerEmail) {
        mOwnerEmail = ownerEmail;
    }

    public int getTotalProduct() {
        return mTotalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        mTotalProduct = totalProduct;
    }

    public boolean isOwner() {
        return mIsOwner;
    }

    public void setOwner(boolean owner) {
        mIsOwner = owner;
    }
}

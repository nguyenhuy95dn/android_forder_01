package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;
import static com.example.duong.android_forder_01.utils.Const.END_INDEX;
import static com.example.duong.android_forder_01.utils.Const.START_INDEX;

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
    private Avatar mAvatar;
    @SerializedName("averate_rating")
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
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;
    @SerializedName("cover_image")
    private CoverImage coverImage;

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

    public Avatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Avatar avatar) {
        mAvatar = avatar;
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

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public String getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        mDeletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getTimeAutoReject() {
        return mTimeAutoReject;
    }

    public void setTimeAutoReject(String timeAutoReject) {
        mTimeAutoReject = timeAutoReject;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public String getFormatTimeTReject() {
        return mTimeAutoReject.substring(START_INDEX, END_INDEX);
    }
}

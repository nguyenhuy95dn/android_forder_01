package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 5/23/2017.
 */

public class RegisterProductInfo {
    @Expose
    @SerializedName("categorie_id")
    private String mCategoryId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("price")
    private String mPrice;
    @Expose
    @SerializedName("image")
    private CollectionImage mImage;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("shop_id")
    private String mShopId;
    @Expose
    @SerializedName("start_hour")
    private String mStartHour;
    @Expose
    @SerializedName("end_hour")
    private String mEndHour;

    public RegisterProductInfo() {
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
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

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public CollectionImage getImage() {
        return mImage;
    }

    public void setImage(CollectionImage image) {
        mImage = image;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getShopId() {
        return mShopId;
    }

    public void setShopId(String shopId) {
        mShopId = shopId;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }
}

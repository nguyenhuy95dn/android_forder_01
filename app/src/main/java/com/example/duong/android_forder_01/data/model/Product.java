package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_PRODUCT;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NAME;
import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_PRICE;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class Product implements Serializable {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("price")
    private double mPrice;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("image")
    private CollectionImage mCollectionImage;
    @SerializedName("start_hour")
    private String mStartHour;
    @SerializedName("end_hour")
    private String mEndHour;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("shop")
    private Shop mShop;
    @SerializedName("user")
    private User mUser;
    @SerializedName("category")
    private Category mCategory;

    public Product(int id, String name, double price, String description,
                   CollectionImage collectionImage,
                   Shop shop) {
        mId = id;
        mName = name;
        mPrice = price;
        mDescription = description;
        mCollectionImage = collectionImage;
        mShop = shop;
    }

    public Product(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT));
        mName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        mPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
        mCollectionImage = new CollectionImage(cursor);
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

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public CollectionImage getCollectionImage() {
        return mCollectionImage;
    }

    public void setCollectionImage(CollectionImage collectionImage) {
        mCollectionImage = collectionImage;
    }

    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public String getFormatPrice() {
        return String.format(FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }

    public String getTime() {
        return mStartHour + " - " + mEndHour;
    }
}

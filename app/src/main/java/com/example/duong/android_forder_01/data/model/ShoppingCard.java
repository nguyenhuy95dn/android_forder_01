package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_DOMAIN;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_SHOP_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_TOTAL;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCard {
    @SerializedName("domain_id")
    private int mDomainId;
    @SerializedName("total_pay")
    private double mTotalPrice;
    @SerializedName("shop_id")
    private int mShopId;
    private String mShopName;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("mStatus")
    private int mStatus;
    @SerializedName("orders")
    private List<ShoppingCardItem> mShoppingCardItems;

    public ShoppingCard() {
    }

    public ShoppingCard(Cursor cursor) {
        mDomainId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_DOMAIN));
        mShopId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOP));
        mShopName = cursor.getString(cursor.getColumnIndex(COLUMN_SHOP_NAME));
        mTotalPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL));
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public List<ShoppingCardItem> getShoppingCardDetails() {
        return mShoppingCardItems;
    }

    public void setShoppingCardDetails(
        List<ShoppingCardItem> shoppingCardItems) {
        mShoppingCardItems = shoppingCardItems;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        mTotalPrice = totalPrice;
    }

    public String getFormatPrice() {
        return String.format(FORMAT_PRICE, getTotalPrice()) + UNIT_MONEY;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }
}

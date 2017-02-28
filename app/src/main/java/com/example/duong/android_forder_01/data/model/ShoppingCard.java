package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import java.util.List;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_DOMAIN;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOP;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_SHOP_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_TOTAL;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCard {
    private int mDomainId;
    private double mTotalPrice;
    private int mShopId;
    private String mShopName;
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
}

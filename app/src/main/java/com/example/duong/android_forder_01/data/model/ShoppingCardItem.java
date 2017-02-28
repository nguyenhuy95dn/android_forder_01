package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_PRODUCT;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOPPING_CARD;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_IMAGE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_PRICE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_QUANTITY;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCardItem {
    private int mDomainId;
    private int mShopId;
    private int mQuantity;
    private int mProductId;
    private String mProductName;
    private String mProductImage;
    private double mPrice;

    public ShoppingCardItem(Cursor cursor) {
        mQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
        mDomainId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOPPING_CARD));
        mShopId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOPPING_CARD));
        mProductId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT));
        mProductName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        mProductImage = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
        mPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }


    public String getFormatPrice() {
        return String.format(FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }
}

package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_END_HOUR;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_PRODUCT;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOPPING_CARD;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_IMAGE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_NAME;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_PRICE;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_QUANTITY;
import static com.example.duong.android_forder_01.data.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_START_HOUR;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_TIME;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCardItem {
    private int mDomainId;
    @SerializedName("user_id")
    private String mUserId;
    private int mShopId;
    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("product_id")
    private int mProductId;
    private String mProductName;
    private String mProductImage;
    private double mPrice;
    private String mStartHour;
    private String mEndHour;
    @SerializedName("status")
    private int mStatus;

    public ShoppingCardItem(Cursor cursor) {
        mQuantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
        mDomainId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOPPING_CARD));
        mShopId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOPPING_CARD));
        mProductId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT));
        mProductName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        mProductImage = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
        mPrice = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE));
        mStartHour = cursor.getString(cursor.getColumnIndex(COLUMN_START_HOUR));
        mEndHour = cursor.getString(cursor.getColumnIndex(COLUMN_END_HOUR));
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

    public boolean isCanOrder() {
        SimpleDateFormat parser = new SimpleDateFormat(FORMAT_TIME);
        Date currentHour = parseDate(parser.format(new Date()));
        Date startHour = parseDate(mStartHour);
        Date endHour = parseDate(mEndHour);
        return currentHour.after(startHour) && currentHour.before(endHour);
    }

    private Date parseDate(String date) {
        SimpleDateFormat parser = new SimpleDateFormat(FORMAT_TIME);
        try {
            return parser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date();
        }
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
        mStatus = status;
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

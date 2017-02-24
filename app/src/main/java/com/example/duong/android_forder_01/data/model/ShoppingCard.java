package com.example.duong.android_forder_01.data.model;

import android.database.Cursor;

import java.util.List;

import static com.example.duong.android_forder_01.data.model.source.local.ShoppingCardContract.ShoppingCardEntry.COLUMN_ID_SHOPPING_CARD;
import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class ShoppingCard {
    private int mId;
    private Domain mDomain;
    private double mTotalPrice;
    private Shop mShop;
    private List<ShoppingCardDetail> mShoppingCardDetails;

    public ShoppingCard() {
    }

    public ShoppingCard(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_SHOPPING_CARD));
        mDomain = new Domain(cursor);
        mShop = new Shop(cursor);
    }

    public Domain getDomain() {
        return mDomain;
    }

    public void setDomain(Domain domain) {
        mDomain = domain;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public List<ShoppingCardDetail> getShoppingCardDetails() {
        return mShoppingCardDetails;
    }

    public void setShoppingCardDetails(
        List<ShoppingCardDetail> shoppingCardDetails) {
        mShoppingCardDetails = shoppingCardDetails;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        mTotalPrice = totalPrice;
    }

    public String getFormatPrice() {
        return String.format(FORMAT_PRICE, mTotalPrice) + UNIT_MONEY;
    }
}

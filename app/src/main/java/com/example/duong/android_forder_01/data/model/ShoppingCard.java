package com.example.duong.android_forder_01.data.model;

import java.util.List;

public class ShoppingCard {
    private int mId;
    private Domain mDomain;
    private double mTotalPrice;
    private List<ShoppingCardDetail> mShoppingCardDetails;

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
}

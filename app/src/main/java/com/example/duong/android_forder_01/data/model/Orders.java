package com.example.duong.android_forder_01.data.model;

public class Orders {
    private String mId;
    private String mStatus;
    private String mEndDate;
    private String mTotalPay;
    private User mUser;
    private Shop mShop;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getTotalPay() {
        return mTotalPay;
    }

    public void setTotalPay(String totalPay) {
        mTotalPay = totalPay;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }
}

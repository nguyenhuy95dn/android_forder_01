package com.example.duong.android_forder_01.data.model;

import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.FORMAT_PRICE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.ACCEPT_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.PENDING_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.REJECT_CODE;
import static com.example.duong.android_forder_01.utils.Const.UNIT_MONEY;

public class Orders {
    private int mId;
    private int mStatus;
    private String mEndDate;
    private double mTotalPay;
    private User mUser;
    private Shop mShop;
    private List<OrderDetail> mOrderDetails;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public double getTotalPay() {
        return mTotalPay;
    }

    public void setTotalPay(double totalPay) {
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

    public List<OrderDetail> getOrderDetails() {
        return mOrderDetails;
    }

    public void setOrderDetails(
        List<OrderDetail> orderDetails) {
        mOrderDetails = orderDetails;
    }

    public boolean isPendingStatus() {
        return mStatus == PENDING_CODE;
    }

    public boolean isAcceptStatus() {
        return mStatus == ACCEPT_CODE;
    }

    public boolean isRejectStatus() {
        return mStatus == REJECT_CODE;
    }

    public boolean isCancel() {
        return mStatus == PENDING_CODE;
    }

    public String getFormatTotalPrice() {
        return String.format(FORMAT_PRICE, mTotalPay) + UNIT_MONEY;
    }
}

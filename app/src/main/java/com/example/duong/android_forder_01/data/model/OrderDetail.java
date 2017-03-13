package com.example.duong.android_forder_01.data.model;

import static com.example.duong.android_forder_01.utils.Const.StatusCode.ACCEPT_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.PENDING_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.REJECT_CODE;

public class OrderDetail {
    private int mId;
    private Orders mOrders;
    private Product mProduct;
    private int mQuantity;
    private int mStatus;
    private boolean mCheckBoxStatus;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public Orders getOrders() {
        return mOrders;
    }

    public void setOrders(Orders orders) {
        mOrders = orders;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
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

    public boolean isCheckBoxStatus() {
        return mCheckBoxStatus;
    }

    public void setCheckBoxStatus(boolean checkBoxStatus) {
        mCheckBoxStatus = checkBoxStatus;
    }
}

package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 6/6/17.
 */

public class OrderManagement {
    @Expose
    @SerializedName("shop_id")
    private int mshopId;
    @Expose
    @SerializedName("order_id")
    private int mOrderId;
    @Expose
    @SerializedName("order_product_id")
    private int mOrderProductId;
    @Expose
    @SerializedName("status")
    private String mStatus;

    public int getMshopId() {
        return mshopId;
    }

    public void setMshopId(int mshopId) {
        this.mshopId = mshopId;
    }

    public int getOrderId() {
        return mOrderId;
    }

    public void setOrderId(int orderId) {
        mOrderId = orderId;
    }

    public int getOrderProductId() {
        return mOrderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        mOrderProductId = orderProductId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}

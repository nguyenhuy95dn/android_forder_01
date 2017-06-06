package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.OrderManagement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 6/6/17.
 */

public class OrderManagerRequest {
    @Expose
    @SerializedName("order")
    private OrderManagement mOrders;

    public OrderManagement getOrders() {
        return mOrders;
    }

    public void setOrders(OrderManagement orders) {
        mOrders = orders;
    }
}

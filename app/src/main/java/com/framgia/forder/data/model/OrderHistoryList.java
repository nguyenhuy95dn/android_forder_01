package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 6/12/17.
 */

public class OrderHistoryList {
    @Expose
    @SerializedName(value = "order_products_done", alternate = { "order_products_reject" })
    private List<OrderHistory> mOrderHistory;

    public List<OrderHistory> getOrderHistory() {
        return mOrderHistory;
    }

    public void setOrderHistory(List<OrderHistory> orderHistory) {
        mOrderHistory = orderHistory;
    }
}

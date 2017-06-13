package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 6/12/17.
 */

public class OrderHistoryList {
    @Expose
    @SerializedName("order_products_done")
    private List<OrderHistory> mListDoneOrders;
    @Expose
    @SerializedName("order_products_reject")
    private List<OrderHistory> mListRejectedOrder;

    public List<OrderHistory> getListDoneOrders() {
        return mListDoneOrders;
    }

    public void setListDoneOrders(List<OrderHistory> listDoneOrders) {
        mListDoneOrders = listDoneOrders;
    }

    public List<OrderHistory> getListRejectedOrder() {
        return mListRejectedOrder;
    }

    public void setListRejectedOrder(List<OrderHistory> listRejectedOrder) {
        mListRejectedOrder = listRejectedOrder;
    }
}

package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.OrderHistoryList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 6/12/17.
 */

public class OrderHistoryShopResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<OrderHistoryList> mOrderHistoryList;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<OrderHistoryList> getOrderHistoryList() {
        return mOrderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistoryList> orderHistoryList) {
        mOrderHistoryList = orderHistoryList;
    }
}

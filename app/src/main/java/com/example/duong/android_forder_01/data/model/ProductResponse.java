package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable {
    @SerializedName("content")
    private List<Product> mProductsList;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("message")
    private String mMessage;

    public List<Product> getProductsList() {
        return mProductsList;
    }

    public void setProductsList(
        List<Product> productsList) {
        mProductsList = productsList;
    }

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
}

package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tri on 26/03/2017.
 */
public class ShopResponse implements Serializable {
    @SerializedName("status")
    private int mStatus;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("content")
    private List<Shop> mShopList;

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

    public List<Shop> getShopList() {
        return mShopList;
    }

    public void setShopList(List<Shop> shopList) {
        mShopList = shopList;
    }
}

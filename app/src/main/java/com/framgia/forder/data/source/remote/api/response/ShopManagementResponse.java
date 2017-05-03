package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.ShopManagement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ShopManagementResponse {

    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<ShopManagement> mListShopManagement;

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

    public List<ShopManagement> getListShopManagement() {
        return mListShopManagement;
    }

    public void setListShopManagement(List<ShopManagement> listShopManagement) {
        mListShopManagement = listShopManagement;
    }
}

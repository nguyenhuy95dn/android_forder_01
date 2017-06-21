package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.ShopInDomain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 6/16/2017.
 */

public class ShopInDomainResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<ShopInDomain> mListShop;

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

    public List<ShopInDomain> getListShop() {
        return mListShop;
    }

    public void setListShop(List<ShopInDomain> listShop) {
        mListShop = listShop;
    }
}

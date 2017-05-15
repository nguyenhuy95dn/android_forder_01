package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 5/15/17.
 */

public class LeaveShopToDomainRequest {
    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;

    public LeaveShopToDomainRequest() {
    }

    public LeaveShopToDomainRequest(int domainId, int shopId) {
        mDomainId = domainId;
        mShopId = shopId;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }
}

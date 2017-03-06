package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tri on 03/03/2017.
 */
public class ShopDomain implements Serializable {
    @SerializedName("domain_id")
    private int mDomainId;
    @SerializedName("status")
    private boolean mStatus;

    public ShopDomain(int domainId, boolean status) {
        mDomainId = domainId;
        mStatus = status;
    }

    public ShopDomain() {
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }
}

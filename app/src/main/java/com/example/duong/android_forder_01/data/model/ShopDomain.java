package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tri on 03/03/2017.
 */
public class ShopDomain implements Serializable {
    @SerializedName("domain_id")
    private Integer mDomainId;
    @SerializedName("status")
    private boolean mStatus;

    public ShopDomain(Integer domainId, boolean status) {
        mDomainId = domainId;
        mStatus = status;
    }

    public ShopDomain() {
    }

    public Integer getDomainId() {
        return mDomainId;
    }

    public void setDomainId(Integer domainId) {
        mDomainId = domainId;
    }

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }
}

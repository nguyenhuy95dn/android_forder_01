package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.example.duong.android_forder_01.utils.Const.StatusCode.ACCEPT_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.PENDING_CODE;
import static com.example.duong.android_forder_01.utils.Const.StatusCode.REJECT_CODE;

/**
 * Created by tri on 03/03/2017.
 */
public class ShopDomain implements Serializable {
    @SerializedName("domain_id")
    private int mDomainId;
    @SerializedName("status")
    private int mStatus;

    public ShopDomain(int domainId, int status) {
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

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public boolean isPendingStatus() {
        return mStatus == PENDING_CODE;
    }

    public boolean isAcceptStatus() {
        return mStatus == ACCEPT_CODE;
    }

    public boolean isRejectStatus() {
        return mStatus == REJECT_CODE;
    }
}

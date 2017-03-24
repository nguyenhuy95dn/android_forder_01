package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static com.example.duong.android_forder_01.utils.Const.Status.ACCEPT_CODE;
import static com.example.duong.android_forder_01.utils.Const.Status.PENDING_CODE;
import static com.example.duong.android_forder_01.utils.Const.Status.REJECT_CODE;

/**
 * Created by tri on 03/03/2017.
 */
public class ShopDomain implements Serializable {
    @SerializedName("domain_id")
    private int mDomainId;
    @SerializedName("status")
    private String mStatus;

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public boolean isPendingStatus() {
        if (mStatus.equals(PENDING_CODE)) return true;
        return false;
    }

    public boolean isAcceptStatus() {
        if (mStatus.equals(ACCEPT_CODE)) return true;
        return false;
    }

    public boolean isRejectStatus() {
        return mStatus == REJECT_CODE;
    }
}

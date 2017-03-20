package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tri on 20/03/2017.
 */
public class DomainResponse implements Serializable {
    @SerializedName("status")
    private Integer mStatus;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("content")
    private List<CollectionDomain> mDomainList;

    public Integer getStatus() {
        return mStatus;
    }

    public void setStatus(Integer status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<CollectionDomain> getDomainList() {
        return mDomainList;
    }

    public void setDomainList(
        List<CollectionDomain> domainList) {
        mDomainList = domainList;
    }
}

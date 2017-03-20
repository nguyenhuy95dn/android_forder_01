package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tri on 20/03/2017.
 */
public class CollectionDomain implements Serializable {
    @SerializedName("domain")
    private Domain mDomain;

    public Domain getDomain() {
        return mDomain;
    }

    public void setDomain(Domain domain) {
        mDomain = domain;
    }
}

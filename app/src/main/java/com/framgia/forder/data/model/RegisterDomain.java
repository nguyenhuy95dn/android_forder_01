package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/9/2017.
 */

public class RegisterDomain {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("status")
    private String mStatus;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}

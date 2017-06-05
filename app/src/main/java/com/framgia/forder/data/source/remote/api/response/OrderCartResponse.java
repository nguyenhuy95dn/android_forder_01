package com.framgia.forder.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/5/2017.
 */

public class OrderCartResponse {
    @SerializedName("status")
    @Expose
    private int mStatus;
    @SerializedName("message")
    @Expose
    private String mMessage;

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
}

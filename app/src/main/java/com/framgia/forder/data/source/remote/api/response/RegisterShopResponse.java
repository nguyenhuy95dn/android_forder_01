package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 5/18/2017.
 */

public class RegisterShopResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<RegisterShopRequest> mRegisterShop;

    public RegisterShopResponse() {
    }

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

    public List<RegisterShopRequest> getRegisterShop() {
        return mRegisterShop;
    }

    public void setRegisterShop(List<RegisterShopRequest> registerShop) {
        mRegisterShop = registerShop;
    }
}

package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 5/17/2017.
 */

public class RegisterShopRequest extends BaseRequest {

    @Expose
    @SerializedName("user_email")
    private String mUserEmail;
    @Expose
    @SerializedName("user_token")
    private String mUserToken;
    @Expose
    @SerializedName("shop")
    private Shop mShop;

    public RegisterShopRequest() {
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public String getUserToken() {
        return mUserToken;
    }

    public void setUserToken(String userToken) {
        mUserToken = userToken;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }
}

package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterShopInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

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
    private RegisterShopInfo mShop;
    private InputStream mImageCover;
    private InputStream mImageAvatar;

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

    public RegisterShopInfo getShop() {
        return mShop;
    }

    public void setShop(RegisterShopInfo shop) {
        mShop = shop;
    }

    public void setImageCover(InputStream imageCover) {
        mImageCover = imageCover;
    }

    public void setImageAvatar(InputStream imageAvatar) {
        mImageAvatar = imageAvatar;
    }

    public InputStream getImageCover() {
        return mImageCover;
    }

    public InputStream getImageAvatar() {
        return mImageAvatar;
    }
}

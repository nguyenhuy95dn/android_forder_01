package com.framgia.forder.data.source.remote.api.request;

import com.framgia.forder.data.model.RegisterShopInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

/**
 * Created by levutantuan on 5/24/17.
 */

public class UpdateShopRequest extends BaseRequest {

    @Expose
    @SerializedName("shop")
    private RegisterShopInfo mShop;
    private InputStream mImageCover;
    private InputStream mImageAvatar;
    private int mShopId;

    public UpdateShopRequest() {
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
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

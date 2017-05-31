package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;

/**
 * Created by levutantuan on 5/24/17.
 */

public class UpdateShopRequest extends BaseRequest {

    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;
    @Expose
    @SerializedName("openforever")
    private boolean mOpenForever;
    @Expose
    @SerializedName("time_auto_close")
    private String mTimeAutoClose;
    private InputStream mImageCover;
    private InputStream mImageAvatar;

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTimeAutoReject() {
        return mTimeAutoReject;
    }

    public void setTimeAutoReject(String timeAutoReject) {
        mTimeAutoReject = timeAutoReject;
    }

    public boolean isOpenForever() {
        return mOpenForever;
    }

    public void setOpenForever(boolean openForever) {
        mOpenForever = openForever;
    }

    public String getTimeAutoClose() {
        return mTimeAutoClose;
    }

    public void setTimeAutoClose(String timeAutoClose) {
        mTimeAutoClose = timeAutoClose;
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

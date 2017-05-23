package com.framgia.forder.data.model;

import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 5/22/2017.
 */

public class RegisterShopInfo {
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("description")
    private String mDescription;
    @Expose
    @SerializedName("avatar")
    private CollectionAvatar mCollectionAvatar;
    @Expose
    @SerializedName("time_auto_reject")
    private String mTimeAutoReject;
    @Expose
    @SerializedName("cover_image")
    private CollectionImage mCoverImage;
    @Expose
    @SerializedName("openforever")
    private boolean mOpenForever;
    @Expose
    @SerializedName("time_auto_close")
    private String mTimeAutoClose;

    public RegisterShopInfo() {
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

    public CollectionAvatar getCollectionAvatar() {
        return mCollectionAvatar;
    }

    public void setCollectionAvatar(CollectionAvatar collectionAvatar) {
        mCollectionAvatar = collectionAvatar;
    }

    public CollectionImage getCoverImage() {
        return mCoverImage;
    }

    public void setCoverImage(CollectionImage coverImage) {
        mCoverImage = coverImage;
    }

    public String getTimeOpenShop() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeAutoReject,
                Utils.INPUT_TIME_FORMAT, Utils.OUTPUT_TIME_FORMAT);
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
}

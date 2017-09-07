package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenhuy95dn on 9/7/2017.
 */

public class NotificationSettingResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private NotificationSettingRequest.Setting mUserSetting;

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

    public NotificationSettingRequest.Setting getUserSetting() {
        return mUserSetting;
    }

    public void setUserSetting(NotificationSettingRequest.Setting userSetting) {
        mUserSetting = userSetting;
    }
}

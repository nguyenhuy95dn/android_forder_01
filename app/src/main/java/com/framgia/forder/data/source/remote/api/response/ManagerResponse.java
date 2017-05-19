package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import com.framgia.forder.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ManagerResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<User> mListUser;

    public ManagerResponse(int status, String message, List<User> listUser) {
        mStatus = status;
        mMessage = message;
        mListUser = listUser;
    }

    public ManagerResponse() {
    }

    protected ManagerResponse(Parcel in) {
        mStatus = in.readInt();
        mMessage = in.readString();
        mListUser = in.createTypedArrayList(User.CREATOR);
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

    public List<User> getListUser() {
        return mListUser;
    }

    public void setListUser(List<User> listUser) {
        mListUser = listUser;
    }
}

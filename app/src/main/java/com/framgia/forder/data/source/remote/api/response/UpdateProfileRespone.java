package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ASUS on 19-04-2017.
 */

public class UpdateProfileRespone implements Parcelable {
    @Expose
    @SerializedName("content")
    private UpdateProfileRequest mUpdateProfileRequest;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;

    protected UpdateProfileRespone(Parcel in) {
        mUpdateProfileRequest = in.readParcelable(UpdateProfileRequest.class.getClassLoader());
        mStatus = in.readInt();
        mMessage = in.readString();
    }

    public static final Creator<UpdateProfileRespone> CREATOR =
            new Creator<UpdateProfileRespone>() {
                @Override
                public UpdateProfileRespone createFromParcel(Parcel in) {
                    return new UpdateProfileRespone(in);
                }

                @Override
                public UpdateProfileRespone[] newArray(int size) {
                    return new UpdateProfileRespone[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mUpdateProfileRequest, flags);
        dest.writeInt(mStatus);
        dest.writeString(mMessage);
    }

    public UpdateProfileRequest getUpdateProfileRequest() {
        return mUpdateProfileRequest;
    }

    public void setUpdateProfileRequest(UpdateProfileRequest updateProfileRequest) {
        mUpdateProfileRequest = updateProfileRequest;
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
}

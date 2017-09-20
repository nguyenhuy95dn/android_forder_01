package com.framgia.forder.data.source.remote.api.response;

import android.os.Parcel;
import android.os.Parcelable;
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
    private List<ManagerDetail> mManagerDetails;

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

    public List<ManagerDetail> getManagerDetails() {
        return mManagerDetails;
    }

    public void setManagerDetails(List<ManagerDetail> managerDetails) {
        mManagerDetails = managerDetails;
    }

    public static class ManagerDetail implements Parcelable {
        public static final Parcelable.Creator<ManagerDetail> CREATOR =
                new Parcelable.Creator<ManagerDetail>() {
                    @Override
                    public ManagerDetail createFromParcel(Parcel in) {
                        return new ManagerDetail(in);
                    }

                    @Override
                    public ManagerDetail[] newArray(int size) {
                        return new ManagerDetail[size];
                    }
                };
        @Expose
        @SerializedName("id")
        private int mId;
        @Expose
        @SerializedName("role")
        private String mRole;
        @Expose
        @SerializedName("user")
        private User mUser;

        private ManagerDetail(Parcel in) {
            mId = in.readInt();
            mRole = in.readString();
            mUser = in.readParcelable(ClassLoader.getSystemClassLoader());
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public String getRole() {
            return mRole;
        }

        public void setRole(String role) {
            mRole = role;
        }

        public User getUser() {
            return mUser;
        }

        public void setUser(User user) {
            mUser = user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mId);
            dest.writeString(mRole);
            dest.writeParcelable(mUser, flags);
        }
    }
}

package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/15/2017.
 */

public final class OwnerShop implements Parcelable {

    public static final Creator<OwnerShop> CREATOR = new Creator<OwnerShop>() {
        @Override
        public OwnerShop createFromParcel(Parcel in) {
            return new OwnerShop(in);
        }

        @Override
        public OwnerShop[] newArray(int size) {
            return new OwnerShop[size];
        }
    };

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("role")
    private String mRole;
    @Expose
    @SerializedName("user_name")
    private String mUserName;

    private OwnerShop(Parcel in) {
        mId = in.readInt();
        mUserId = in.readInt();
        mShopId = in.readInt();
        mRole = in.readString();
        mUserName = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mUserId);
        dest.writeInt(mShopId);
        dest.writeString(mRole);
        dest.writeString(mUserName);
    }
}

package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/8/2017.
 */

public final class DomainManagement implements Parcelable {

    public static final Creator<DomainManagement> CREATOR = new Creator<DomainManagement>() {
        @Override
        public DomainManagement createFromParcel(Parcel in) {
            return new DomainManagement(in);
        }

        @Override
        public DomainManagement[] newArray(int size) {
            return new DomainManagement[size];
        }
    };

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("created_at")
    private String mCreateAt;
    @Expose
    @SerializedName("updated_at")
    private String mUpdateAt;
    @Expose
    @SerializedName("slug")
    private String mSlug;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("owner")
    private int mOwner;
    @Expose
    @SerializedName("count_user")
    private int mCountUser;
    @Expose
    @SerializedName("count_shop")
    private int mCountShop;
    @Expose
    @SerializedName("count_product")
    private int mCountProduct;
    @Expose
    @SerializedName("role_of_current_user")
    private String mRoleOfCurrentUser;
    private boolean mIsOwner;

    private DomainManagement(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mCreateAt = in.readString();
        mUpdateAt = in.readString();
        mSlug = in.readString();
        mStatus = in.readString();
        mOwner = in.readInt();
        mCountUser = in.readInt();
        mCountShop = in.readInt();
        mCountProduct = in.readInt();
        mRoleOfCurrentUser = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(String createAt) {
        mCreateAt = createAt;
    }

    public String getUpdateAt() {
        return mUpdateAt;
    }

    public void setUpdateAt(String updateAt) {
        mUpdateAt = updateAt;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getOwner() {
        return mOwner;
    }

    public void setOwner(int owner) {
        mOwner = owner;
    }

    public int getCountUser() {
        return mCountUser;
    }

    public void setCountUser(int countUser) {
        mCountUser = countUser;
    }

    public int getCountShop() {
        return mCountShop;
    }

    public void setCountShop(int countShop) {
        mCountShop = countShop;
    }

    public int getCountProduct() {
        return mCountProduct;
    }

    public void setCountProduct(int countProduct) {
        mCountProduct = countProduct;
    }

    public String getRoleOfCurrentUser() {
        return mRoleOfCurrentUser;
    }

    public void setRoleOfCurrentUser(String roleOfCurrentUser) {
        mRoleOfCurrentUser = roleOfCurrentUser;
    }

    public boolean isOwner() {
        return mIsOwner;
    }

    public void setOwner(boolean owner) {
        mIsOwner = owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mCreateAt);
        dest.writeString(mUpdateAt);
        dest.writeString(mSlug);
        dest.writeString(mStatus);
        dest.writeInt(mOwner);
        dest.writeInt(mCountUser);
        dest.writeInt(mCountShop);
        dest.writeInt(mCountProduct);
        dest.writeString(mRoleOfCurrentUser);
    }
}

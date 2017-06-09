package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Age on 6/8/2017.
 */

public class DomainManagement {
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
}

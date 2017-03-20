package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tri on 20/03/2017.
 */
public class CategoryResponse implements Serializable {
    @SerializedName("status")
    private int mStatus;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("content")
    private List<Category> mCategoryList;

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

    public List<Category> getCategoryList() {
        return mCategoryList;
    }

    public void setCategoryList(
        List<Category> categoryList) {
        mCategoryList = categoryList;
    }
}

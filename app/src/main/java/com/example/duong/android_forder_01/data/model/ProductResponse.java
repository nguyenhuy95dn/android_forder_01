package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    private List<Product> mProductsList;
    @SerializedName("next_products")
    private String mNextProducts;
    @SerializedName("product_end")
    private boolean mProductEnd;

    public List<Product> getProductsList() {
        return mProductsList;
    }

    public void setProductsList(
        List<Product> productsList) {
        mProductsList = productsList;
    }

    public String getNextProducts() {
        return mNextProducts;
    }

    public void setNextProducts(String nextProducts) {
        mNextProducts = nextProducts;
    }

    public boolean isProductEnd() {
        return mProductEnd;
    }

    public void setProductEnd(boolean productEnd) {
        mProductEnd = productEnd;
    }
}

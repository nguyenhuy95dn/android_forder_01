package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Age on 4/20/2017.
 */

public class SearchResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private Search mSearch;

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

    public Search getSearch() {
        return mSearch;
    }

    public void setSearch(Search search) {
        mSearch = search;
    }

    public class Search {
        @Expose
        @SerializedName("products")
        private List<Product> mProductList;
        @Expose
        @SerializedName("shops")
        private List<Shop> mShopList;

        public List<Product> getProductList() {
            return mProductList;
        }

        public void setProductList(List<Product> productList) {
            mProductList = productList;
        }

        public List<Shop> getShopList() {
            return mShopList;
        }

        public void setShopList(List<Shop> shopList) {
            mShopList = shopList;
        }
    }
}

package com.framgia.forder.data.source.remote.api.response;

import com.framgia.forder.data.model.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 7/18/2017.
 */

public class ShopRequestResponse {

    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<ShopContain> mShopContains;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public List<ShopContain> getShopContains() {
        return mShopContains;
    }

    public void setShopContains(List<ShopContain> shopContains) {
        mShopContains = shopContains;
    }

    public static class ShopContain {
        @Expose
        @SerializedName("domain_id")
        private int mDomainId;
        @Expose
        @SerializedName("shop_id")
        private int mShopId;
        @Expose
        @SerializedName("status")
        private String mStatus;
        @Expose
        @SerializedName("shop")
        private Shop mShop;

        public int getDomainId() {
            return mDomainId;
        }

        public void setDomainId(int domainId) {
            mDomainId = domainId;
        }

        public int getShopId() {
            return mShopId;
        }

        public void setShopId(int shopId) {
            mShopId = shopId;
        }

        public String getStatus() {
            return mStatus;
        }

        public void setStatus(String status) {
            mStatus = status;
        }

        public Shop getShop() {
            return mShop;
        }

        public void setShop(Shop shop) {
            mShop = shop;
        }
    }
}

package com.framgia.forder.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 7/13/2017.
 */

public class DomainToRequestShopResponse {
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("message")
    private String mMessage;
    @Expose
    @SerializedName("content")
    private List<DomainToRequest> mDomainToRequestList;

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

    public List<DomainToRequest> getDomainToRequestList() {
        return mDomainToRequestList;
    }

    public void setDomainToRequestList(List<DomainToRequest> domainToRequestList) {
        mDomainToRequestList = domainToRequestList;
    }

    public static class DomainToRequest {
        @Expose
        @SerializedName("domain_id")
        private int mDomainId;
        @Expose
        @SerializedName("status")
        private int mStatus;
        @Expose
        @SerializedName("domain_name")
        private int mDomainName;
        @Expose
        @SerializedName("number_of_users")
        private int mNumberOfUsers;
        @Expose
        @SerializedName("number_of_shops")
        private int mNumberOfShops;
        @Expose
        @SerializedName("number_of_products")
        private int mNumberOfProducts;

        public int getDomainId() {
            return mDomainId;
        }

        public void setDomainId(int domainId) {
            mDomainId = domainId;
        }

        public int getStatus() {
            return mStatus;
        }

        public void setStatus(int status) {
            mStatus = status;
        }

        public int getDomainName() {
            return mDomainName;
        }

        public void setDomainName(int domainName) {
            mDomainName = domainName;
        }

        public int getNumberOfUsers() {
            return mNumberOfUsers;
        }

        public void setNumberOfUsers(int numberOfUsers) {
            mNumberOfUsers = numberOfUsers;
        }

        public int getNumberOfShops() {
            return mNumberOfShops;
        }

        public void setNumberOfShops(int numberOfShops) {
            mNumberOfShops = numberOfShops;
        }

        public int getNumberOfProducts() {
            return mNumberOfProducts;
        }

        public void setNumberOfProducts(int numberOfProducts) {
            mNumberOfProducts = numberOfProducts;
        }
    }
}

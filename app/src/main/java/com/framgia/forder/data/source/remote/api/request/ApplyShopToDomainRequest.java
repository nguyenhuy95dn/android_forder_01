package com.framgia.forder.data.source.remote.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 5/11/17.
 */

public class ApplyShopToDomainRequest extends BaseRequest {

    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("domain_name")
    private String mDomainName;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("shop_name")
    private String mShopName;
    @Expose
    @SerializedName("user_name")
    private String mUserName;
    @Expose
    @SerializedName("number_of_products")
    private int mNumberProduct;

    public ApplyShopToDomainRequest(int domainId, String domainName, int shopId, String shopName,
            String userName, int numberProduct) {
        mDomainId = domainId;
        mDomainName = domainName;
        mShopId = shopId;
        mShopName = shopName;
        mUserName = userName;
        mNumberProduct = numberProduct;
    }

    public ApplyShopToDomainRequest() {
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public String getDomainName() {
        return mDomainName;
    }

    public void setDomainName(String domainName) {
        mDomainName = domainName;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getNumberProduct() {
        return mNumberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        mNumberProduct = numberProduct;
    }
}

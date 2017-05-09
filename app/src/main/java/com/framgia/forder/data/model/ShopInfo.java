package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ShopInfo {
    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("domain_name")
    private String mDomainName;
    @Expose
    @SerializedName("number_of_users")
    private int mNumberUser;
    @Expose
    @SerializedName("number_of_shops")
    private int mNumberShop;
    @Expose
    @SerializedName("number_of_products")
    private int mNumberProduct;
    @Expose
    @SerializedName("domain")
    private Domain mDomain;

    public ShopInfo(int domainId, String domainName, int numberUser, int numberShop,
            int numberProduct, Domain domain) {
        mDomainId = domainId;
        mDomainName = domainName;
        mNumberUser = numberUser;
        mNumberShop = numberShop;
        mNumberProduct = numberProduct;
        mDomain = domain;
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

    public int getNumberUser() {
        return mNumberUser;
    }

    public void setNumberUser(int numberUser) {
        mNumberUser = numberUser;
    }

    public int getNumberShop() {
        return mNumberShop;
    }

    public void setNumberShop(int numberShop) {
        mNumberShop = numberShop;
    }

    public int getNumberProduct() {
        return mNumberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        mNumberProduct = numberProduct;
    }

    public Domain getDomain() {
        return mDomain;
    }

    public void setDomain(Domain domain) {
        mDomain = domain;
    }
}

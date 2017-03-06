package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tri on 03/03/2017.
 */
public class ShopInfo implements Serializable {
    @SerializedName("domain_id")
    private int mDomainId;
    @SerializedName("domain_name")
    private String mDomainName;
    @SerializedName("number_of_users")
    private int mNumberOfUsers;
    @SerializedName("number_of_shops")
    private int mNumberOfShops;
    @SerializedName("number_of_products")
    private int mNumberOfProducts;

    public ShopInfo(int domainId, String domainName, int numberOfUsers, int numberOfShops,
                    int numberOfProducts) {
        mDomainId = domainId;
        mDomainName = domainName;
        mNumberOfUsers = numberOfUsers;
        mNumberOfShops = numberOfShops;
        mNumberOfProducts = numberOfProducts;
    }

    public ShopInfo() {
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        this.mDomainId = domainId;
    }

    public String getDomainName() {
        return mDomainName;
    }

    public void setDomainName(String domainName) {
        this.mDomainName = domainName;
    }

    public int getNumberOfUsers() {
        return mNumberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.mNumberOfUsers = numberOfUsers;
    }

    public int getNumberOfShops() {
        return mNumberOfShops;
    }

    public void setNumberOfShops(int numberOfShops) {
        this.mNumberOfShops = numberOfShops;
    }

    public int getNumberOfProducts() {
        return mNumberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.mNumberOfProducts = numberOfProducts;
    }
}

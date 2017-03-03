package com.example.duong.android_forder_01.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tri on 02/03/2017.
 */
public class ShopManagement implements Serializable {
    @SerializedName("shop")
    private Shop mShop;
    @SerializedName("shop_domain")
    private List<ShopDomain> mShopDomainList;
    @SerializedName("shop_info")
    private List<ShopInfo> mShopInfoList;

    public ShopManagement(Shop shop,
                          List<ShopDomain> shopDomainList,
                          List<ShopInfo> shopInfoList) {
        mShop = shop;
        mShopDomainList = shopDomainList;
        mShopInfoList = shopInfoList;
    }

    public ShopManagement() {
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public List<ShopDomain> getShopDomainList() {
        return mShopDomainList;
    }

    public void setShopDomainList(
        List<ShopDomain> shopDomainList) {
        mShopDomainList = shopDomainList;
    }

    public List<ShopInfo> getShopInfoList() {
        return mShopInfoList;
    }

    public void setShopInfoList(
        List<ShopInfo> shopInfoList) {
        mShopInfoList = shopInfoList;
    }
}

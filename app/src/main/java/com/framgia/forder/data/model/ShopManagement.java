package com.framgia.forder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 5/4/17.
 */

public class ShopManagement {

    @Expose
    @SerializedName("shop")
    private Shop mShop;
    @Expose
    @SerializedName("shop_domain")
    private List<Domain> mShopDomains;
    @Expose
    @SerializedName("shop_info")
    private List<ShopInfo> mShopInfos;

    public ShopManagement(Shop shop, List<Domain> shopDomain, List<ShopInfo> shopInfo) {
        mShop = shop;
        mShopDomains = shopDomain;
        mShopInfos = shopInfo;
    }

    public Shop getShop() {
        return mShop;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public List<Domain> getShopDomains() {
        return mShopDomains;
    }

    public void setShopDomains(List<Domain> shopDomains) {
        mShopDomains = shopDomains;
    }

    public List<ShopInfo> getShopInfos() {
        return mShopInfos;
    }

    public void setShopInfos(List<ShopInfo> shopInfos) {
        mShopInfos = shopInfos;
    }
}

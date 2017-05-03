package com.framgia.forder.data.model;

import android.os.Parcel;
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
    private List<Shop> mShopInfos;

    private ShopManagement(Parcel in) {
        mShop = in.readParcelable(Shop.class.getClassLoader());
        mShopDomains = in.readParcelable(Domain.class.getClassLoader());
        mShopInfos = in.readParcelable(Shop.class.getClassLoader());
    }

    public ShopManagement(Shop shop, List<Domain> shopDomain, List<Shop> shopInfo) {
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

    public List<Shop> getShopInfos() {
        return mShopInfos;
    }

    public void setShopInfos(List<Shop> shopInfos) {
        mShopInfos = shopInfos;
    }
}

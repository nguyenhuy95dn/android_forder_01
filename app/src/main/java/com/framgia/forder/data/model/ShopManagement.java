package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by levutantuan on 5/4/17.
 */

public class ShopManagement implements Parcelable {

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

    public ShopManagement() {
    }

    protected ShopManagement(Parcel in) {
        mShop = in.readParcelable(Shop.class.getClassLoader());
        mShopDomains = in.createTypedArrayList(Domain.CREATOR);
        mShopInfos = in.createTypedArrayList(ShopInfo.CREATOR);
    }

    public static final Creator<ShopManagement> CREATOR = new Creator<ShopManagement>() {
        @Override
        public ShopManagement createFromParcel(Parcel in) {
            return new ShopManagement(in);
        }

        @Override
        public ShopManagement[] newArray(int size) {
            return new ShopManagement[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mShop, flags);
        dest.writeTypedList(mShopDomains);
        dest.writeTypedList(mShopInfos);
    }
}

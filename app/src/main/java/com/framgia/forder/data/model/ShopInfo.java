package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ShopInfo implements Parcelable {
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

    public ShopInfo() {
    }

    protected ShopInfo(Parcel in) {
        mDomainId = in.readInt();
        mDomainName = in.readString();
        mNumberUser = in.readInt();
        mNumberShop = in.readInt();
        mNumberProduct = in.readInt();
        mDomain = in.readParcelable(Domain.class.getClassLoader());
    }

    public static final Creator<ShopInfo> CREATOR = new Creator<ShopInfo>() {
        @Override
        public ShopInfo createFromParcel(Parcel in) {
            return new ShopInfo(in);
        }

        @Override
        public ShopInfo[] newArray(int size) {
            return new ShopInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDomainId);
        dest.writeString(mDomainName);
        dest.writeInt(mNumberUser);
        dest.writeInt(mNumberShop);
        dest.writeInt(mNumberProduct);
        dest.writeParcelable(mDomain, flags);
    }
}

package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Locale;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 20/04/2017.
 */

public class CartItem implements Parcelable {
    public static final Parcelable.Creator<CartItem> CREATOR = new Parcelable.Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    @SerializedName("product_id")
    @Expose
    private int mProductId;
    @SerializedName("price")
    @Expose
    private double mPrice;
    @SerializedName("quantity")
    @Expose
    private int mQuantity;
    @Expose
    @SerializedName("notes")
    private String mNotes;
    private int mDomainId;
    private int mShopId;
    private String mProductName;
    private String mProductImage;
    private String mStartHour;
    private String mEndHour;
    private Double mTotal;

    public CartItem() {
    }

    public CartItem(int domainId, int shopId, int quantity, int productId, String productName,
            String productImage, double price, String startHour, String endHour, Double total,
            String notes) {
        mDomainId = domainId;
        mShopId = shopId;
        mQuantity = quantity;
        mProductId = productId;
        mProductName = productName;
        mProductImage = productImage;
        mPrice = price;
        mStartHour = startHour;
        mEndHour = endHour;
        mTotal = total;
        mNotes = notes;
    }

    private CartItem(Parcel in) {
        mProductId = in.readInt();
        mPrice = in.readDouble();
        mQuantity = in.readInt();
        mNotes = in.readString();
        mDomainId = in.readInt();
        mShopId = in.readInt();
        mProductName = in.readString();
        mProductImage = in.readString();
        mStartHour = in.readString();
        mEndHour = in.readString();
        mTotal = in.readDouble();
    }

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

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String productImage) {
        mProductImage = productImage;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public void setStartHour(String startHour) {
        mStartHour = startHour;
    }

    public String getEndHour() {
        return mEndHour;
    }

    public void setEndHour(String endHour) {
        mEndHour = endHour;
    }

    public String getFormatPrice() {
        return String.format(Locale.ENGLISH, FORMAT_PRICE, mTotal) + UNIT_MONEY;
    }

    public Double getTotal() {
        return mQuantity * mPrice;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mProductId);
        dest.writeDouble(mPrice);
        dest.writeDouble(mQuantity);
        dest.writeString(mNotes);
        dest.writeInt(mDomainId);
        dest.writeInt(mShopId);
        dest.writeString(mProductName);
        dest.writeString(mProductImage);
        dest.writeString(mStartHour);
        dest.writeString(mEndHour);
        dest.writeDouble(mTotal);
    }
}

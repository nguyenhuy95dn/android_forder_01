package com.framgia.forder.data.model;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

/**
 * Created by tri on 20/04/2017.
 */

public class CartItem {
    private int mDomainId;
    private int mShopId;
    private int mQuantity;
    private int mProductId;
    private String mProductName;
    private String mProductImage;
    private double mPrice;
    private String mStartHour;
    private String mEndHour;

    public CartItem() {
    }

    public CartItem(int domainId, int shopId, int quantity, int productId, String productName,
            String productImage, double price, String startHour, String endHour) {
        mDomainId = domainId;
        mShopId = shopId;
        mQuantity = quantity;
        mProductId = productId;
        mProductName = productName;
        mProductImage = productImage;
        mPrice = price;
        mStartHour = startHour;
        mEndHour = endHour;
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
        return String.format(FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }
}

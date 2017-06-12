package com.framgia.forder.data.model;

import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Locale;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;
import static com.framgia.forder.utils.Constant.VERTICAL_COLUMN;

/**
 * Created by levutantuan on 6/12/17.
 */

public class OrderHistory {
    @Expose
    @SerializedName("id")
    private int mIdOrder;
    @Expose
    @SerializedName("quantity")
    private int mQuantity;
    @Expose
    @SerializedName("price")
    private double mPrice;
    @Expose
    @SerializedName("product_id")
    private int mProductId;
    @Expose
    @SerializedName("created_at")
    private String mTimeCreateOrder;
    @Expose
    @SerializedName("status")
    private String mStatus;
    @Expose
    @SerializedName("name")
    private String mNameProduct;

    public int getIdOrder() {
        return mIdOrder;
    }

    public void setIdOrder(int idOrder) {
        mIdOrder = idOrder;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public String getTimeCreateOrder() {
        return mTimeCreateOrder;
    }

    public void setTimeCreateOrder(String timeCreateOrder) {
        mTimeCreateOrder = timeCreateOrder;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getNameProduct() {
        return mNameProduct;
    }

    public void setNameProduct(String nameProduct) {
        mNameProduct = nameProduct;
    }

    public String getPriceFormat() {
        return String.format(Locale.ENGLISH, FORMAT_PRICE, mPrice) + UNIT_MONEY;
    }

    public String getTimeOrderFormat() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeCreateOrder,
                Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT + VERTICAL_COLUMN + Utils.OUTPUT_DATE_FORMAT);
    }
}

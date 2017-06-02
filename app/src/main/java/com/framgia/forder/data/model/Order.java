package com.framgia.forder.data.model;

import com.framgia.forder.utils.OrderStatusCode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;

public class Order {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("user_name")
    private String mUserName;
    @Expose
    @SerializedName("domain_id")
    private int mDomainId;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @OrderStatusCode
    private int mStatus;
    @Expose
    @SerializedName("status")
    private String mStatusOrder;
    @Expose
    @SerializedName("end_date")
    private String mEndDate;
    @Expose
    @SerializedName("total_pay")
    private double mTotalPay;
    @Expose
    @SerializedName("created_at")
    private String mTimeCreateOrder;
    @Expose
    @SerializedName("user")
    private User mUser;
    @Expose
    @SerializedName("shop")
    private Shop mShop;
    @Expose
    @SerializedName("order_products")
    private List<OrderDetail> mOrderDetails;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @OrderStatusCode
    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public double getTotalPay() {
        return mTotalPay;
    }

    public void setTotalPay(double totalPay) {
        mTotalPay = totalPay;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getTimeCreateOrder() {
        return mTimeCreateOrder;
    }

    public void setTimeCreateOrder(String timeCreateOrder) {
        mTimeCreateOrder = timeCreateOrder;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public Shop getShop() {
        return mShop;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    public void setShop(Shop shop) {
        mShop = shop;
    }

    public List<OrderDetail> getOrderDetails() {
        return mOrderDetails;
    }

    public String getStatusOrder() {
        return mStatusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        mStatusOrder = statusOrder;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        mOrderDetails = orderDetails;
    }

    public String getFormatTotalPrice() {
        return String.format(FORMAT_PRICE, mTotalPay) + UNIT_MONEY;
    }
}

package com.framgia.forder.data.model;

import com.framgia.forder.R;
import com.framgia.forder.utils.OrderStatusCode;
import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;
import static com.framgia.forder.utils.Constant.VERTICAL_COLUMN;

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
    @Expose
    @SerializedName("status")
    private StatusOders mStatusOrder;
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
    @OrderStatusCode
    private int mStatus;
    @Expose
    @SerializedName("order_products")
    private List<OrderDetail> mOrderDetails;
    @Expose
    @SerializedName("is_paid")
    private boolean mIsPaid;

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

    public StatusOders getStatusOrder() {
        return mStatusOrder;
    }

    public void setStatusOrder(StatusOders statusOrder) {
        mStatusOrder = statusOrder;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        mOrderDetails = orderDetails;
    }

    public String getTotalPriceFormat() {
        return String.format(FORMAT_PRICE, mTotalPay) + UNIT_MONEY;
    }

    public boolean isPaid() {
        return mIsPaid;
    }

    public void setPaid(boolean paid) {
        mIsPaid = paid;
    }

    public String getTimeOrderFormat() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeCreateOrder,
                Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT + VERTICAL_COLUMN + Utils.OUTPUT_DATE_FORMAT);
    }

    public int getStatusColor() {
        if (mStatusOrder == StatusOders.Done) {
            return R.drawable.button_green;
        } else if (mStatusOrder == StatusOders.Rejected) {
            return R.drawable.button_red;
        } else if (mStatusOrder == StatusOders.Pending) {
            return R.drawable.button_blue;
        } else if (mStatusOrder == StatusOders.Accepted) {
            return R.drawable.button_orange;
        }
        return 0;
    }

    public enum StatusOders {
        @Expose
        @SerializedName("rejected")
        Rejected("rejected"),
        @Expose
        @SerializedName("pending")
        Pending("pending"),
        @Expose
        @SerializedName("accepted")
        Accepted("accepted"),
        @Expose
        @SerializedName("done")
        Done("done");

        private final String mValue;

        StatusOders(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue;
        }

        public static StatusOders getStatusOrders(String value) {
            if ("rejected".equals(value)) {
                return Rejected;
            } else if ("pending".equals(value)) {
                return Pending;
            } else if ("accepted".equals(value)) {
                return Accepted;
            } else if ("done".equals(value)) {
                return Done;
            }
            return null;
        }
    }
}

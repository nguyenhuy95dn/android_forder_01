package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.forder.R;
import com.framgia.forder.utils.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.framgia.forder.utils.Constant.FORMAT_PRICE;
import static com.framgia.forder.utils.Constant.UNIT_MONEY;
import static com.framgia.forder.utils.Constant.VERTICAL_COLUMN;

public class OrderDetail implements Parcelable {

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("shop_id")
    private int mShopId;
    @Expose
    @SerializedName("product")
    private Product mProduct;
    @Expose
    @SerializedName("quantity")
    private int mQuantity;
    @Expose
    @SerializedName("product_price")
    private double mPrice;
    @Expose
    @SerializedName("notes")
    private String mNotes;
    @Expose
    @SerializedName("user_id")
    private int mUserId;
    @Expose
    @SerializedName("product_id")
    private int mProductId;
    @Expose
    @SerializedName("product_name")
    private String mProductName;
    @Expose
    @SerializedName("created_at")
    private String mTimeCreateOrder;
    @Expose
    @SerializedName("status")
    private StatusOders mStatusOrder;
    @Expose
    @SerializedName("product_image")
    private Image mImageProduct;
    //Todo edit later
    private int mStatus;
    //Todo edit later
    private boolean mCheckBoxStatus;

    public OrderDetail() {
    }

    private OrderDetail(Parcel in) {
        mId = in.readInt();
        mShopId = in.readInt();
        mQuantity = in.readInt();
        mPrice = in.readDouble();
        mNotes = in.readString();
        mUserId = in.readInt();
        mProductId = in.readInt();
        mProductName = in.readString();
        mTimeCreateOrder = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mShopId);
        dest.writeInt(mQuantity);
        dest.writeDouble(mPrice);
        dest.writeString(mNotes);
        dest.writeInt(mUserId);
        dest.writeInt(mProductId);
        dest.writeString(mProductName);
        dest.writeString(mTimeCreateOrder);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public Image getImageProduct() {
        return mImageProduct;
    }

    public void setImageProduct(Image imageProduct) {
        mImageProduct = imageProduct;
    }

    //Todo edit later
    public int getStatus() {
        return mStatus;
    }

    //Todo edit later
    public void setStatus(int status) {
        mStatus = status;
    }

    public StatusOders getStatusOrder() {
        return mStatusOrder;
    }

    public void setStatusOrder(StatusOders statusOrder) {
        mStatusOrder = statusOrder;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getTimeCreateOrder() {
        return mTimeCreateOrder;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }

    public void setTimeCreateOrder(String timeCreateOrder) {
        mTimeCreateOrder = timeCreateOrder;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    //Todo edit later
    public boolean isCheckBoxStatus() {
        return mCheckBoxStatus;
    }

    public String getTotalPriceFormat() {
        return String.format(FORMAT_PRICE, mPrice * mQuantity) + UNIT_MONEY;
    }

    public String getTimeOrderFormat() {
        return Utils.DateTimeUntils.convertUiFormatToDataFormat(mTimeCreateOrder,
                Utils.INPUT_TIME_FORMAT,
                Utils.OUTPUT_TIME_FORMAT + VERTICAL_COLUMN + Utils.OUTPUT_DATE_FORMAT);
    }

    public int getStatusColor() {
        if (mStatusOrder == StatusOders.Done) {
            return R.drawable.button_blue;
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

        public static OrderDetail.StatusOders getStatusOrders(String value) {
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

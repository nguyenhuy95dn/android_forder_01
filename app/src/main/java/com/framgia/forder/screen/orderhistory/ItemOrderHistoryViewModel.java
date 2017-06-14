package com.framgia.forder.screen.orderhistory;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.OrderDetail;

/**
 * Created by ASUS on 28-04-2017.
 */

public class ItemOrderHistoryViewModel extends BaseObservable {
    private OrderDetail mOrderDetail;

    ItemOrderHistoryViewModel(OrderDetail orderDetail) {
        mOrderDetail = orderDetail;
    }

    public String getProductImage() {
        if (mOrderDetail != null && mOrderDetail.getImageProduct() != null) {
            return mOrderDetail.getImageProduct().getUrl();
        }
        return "";
    }

    public String getProductQuantity() {
        return mOrderDetail != null ? Integer.toString(mOrderDetail.getQuantity()) : "";
    }

    public String getProductName() {
        return mOrderDetail.getProductName();
    }

    public String getProductPrice() {
        return mOrderDetail.getTotalPriceFormat();
    }

    public String getStatus() {
        return String.valueOf(mOrderDetail.getStatusOrder());
    }

    @Bindable
    public int getStatusColor() {
        return mOrderDetail.getStatusColor();
    }
}

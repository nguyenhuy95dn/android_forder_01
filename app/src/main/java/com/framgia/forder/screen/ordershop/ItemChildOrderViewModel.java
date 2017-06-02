package com.framgia.forder.screen.ordershop;

import com.framgia.forder.data.model.OrderDetail;

/**
 * Created by levutantuan on 6/1/17.
 */

public class ItemChildOrderViewModel {
    private final OrderDetail mOrderDetail;

    ItemChildOrderViewModel(OrderDetail orderDetail) {
        mOrderDetail = orderDetail;
    }

    public String getStatus() {
        if (mOrderDetail != null) {
            return mOrderDetail.getStatusOrder();
        }
        return "";
    }

    public int getQuantity() {
        if (mOrderDetail != null) {
            return mOrderDetail.getQuantity();
        }
        return 0;
    }

    public String getPrice() {
        if (mOrderDetail != null) {
            return mOrderDetail.getFormatTotalPrice();
        }
        return "";
    }

    public String getNotes() {
        if (mOrderDetail != null) {
            return mOrderDetail.getNotes();
        }
        return "";
    }
}

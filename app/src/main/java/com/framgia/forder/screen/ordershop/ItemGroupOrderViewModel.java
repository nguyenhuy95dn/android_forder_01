package com.framgia.forder.screen.ordershop;

import com.framgia.forder.data.model.Order;

/**
 * Created by levutantuan on 6/1/17.
 */

public class ItemGroupOrderViewModel {
    private final Order mOrder;

    ItemGroupOrderViewModel(Order order) {
        mOrder = order;
    }

    public String getStatus() {
        if (mOrder != null) {
            return mOrder.getStatusOrder();
        }
        return "";
    }

    public String getTimeCreateOrder() {
        if (mOrder != null) {
            return mOrder.getTimeCreateOrder();
        }
        return "";
    }
}

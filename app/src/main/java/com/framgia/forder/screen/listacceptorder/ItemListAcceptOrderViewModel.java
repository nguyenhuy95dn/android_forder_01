package com.framgia.forder.screen.listacceptorder;

import com.framgia.forder.data.model.OrderDetail;

/**
 * Created by nguyenhuy95dn on 7/6/2017.
 */

public class ItemListAcceptOrderViewModel {

    private final OrderDetail mOrderDetail;

    ItemListAcceptOrderViewModel(OrderDetail orderDetail) {
        mOrderDetail = orderDetail;
    }

    public String getName() {
        return mOrderDetail.getProductName();
    }

    public String getQuantity() {
        return String.valueOf(mOrderDetail.getQuantity());
    }

    public String getPrice() {
        return mOrderDetail.getTotalPriceFormat();
    }
}

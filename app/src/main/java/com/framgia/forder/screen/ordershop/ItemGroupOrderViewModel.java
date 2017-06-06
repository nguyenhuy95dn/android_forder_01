package com.framgia.forder.screen.ordershop;

import android.content.Context;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderManagement;

/**
 * Created by levutantuan on 6/1/17.
 */

public class ItemGroupOrderViewModel {

    private final Context mContext;
    private final Order mOrder;
    private final OrderShopAdapter.OrderManagementListener mOrderManagementListener;

    ItemGroupOrderViewModel(Context context, Order order,
            OrderShopAdapter.OrderManagementListener orderManagementListener) {
        mContext = context;
        mOrder = order;
        mOrderManagementListener = orderManagementListener;
    }

    public String getStatus() {
        if (mOrder != null) {
            return mOrder.getStatusOrder();
        }
        return "";
    }

    public String getNameUser() {
        if (mOrder != null) {
            return mOrder.getUserName();
        }
        return "";
    }

    public String getTimeCreateOrder() {
        if (mOrder != null) {
            return mOrder.getTimeOrderFormat();
        }
        return "";
    }

    public String getPrice() {
        if (mOrder != null) {
            return mOrder.getTotalPriceFormat();
        }
        return "";
    }

    public void onClickAcceptOrder() {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setShopId(mOrder.getShopId());
        orderManagement.setStatus(mContext.getString(R.string.accepted_status));
        orderManagement.setOrderId(mOrder.getId());
        mOrderManagementListener.onAcceptOrRejectOrderManager(orderManagement);
    }

    public void onClickRejectOrder() {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setShopId(mOrder.getShopId());
        orderManagement.setStatus(mContext.getString(R.string.rejected_status));
        orderManagement.setOrderId(mOrder.getId());
        mOrderManagementListener.onAcceptOrRejectOrderManager(orderManagement);
    }

}

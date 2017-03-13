package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder;

import com.example.duong.android_forder_01.data.model.OrderDetail;

/**
 * Created by Duong on 3/10/2017.
 */
public class CheckOrderActionHandler {
    private CheckOrderContract.Presenter mListener;

    public CheckOrderActionHandler(CheckOrderContract.Presenter listener) {
        mListener = listener;
    }

    public void checkAll() {
        if (mListener == null) return;
        mListener.checkAll();
    }

    public void checkOne(OrderDetail orderDetail) {
        if (mListener == null) return;
        mListener.checkOne(orderDetail);
    }

    public void acceptAll() {
        if (mListener == null) return;
        mListener.acceptAll();
    }

    public void acceptOne(OrderDetail orderDetail) {
        if (mListener == null) return;
        mListener.acceptOne(orderDetail);
    }

    public void rejectAll() {
        if (mListener == null) return;
        mListener.rejectAll();
    }

    public void rejectOne(OrderDetail orderDetail) {
        if (mListener == null) return;
        mListener.rejectOne(orderDetail);
    }

    public void continueCheckOrder() {
        if (mListener == null) return;
        mListener.continueCheckOrder();
    }
}

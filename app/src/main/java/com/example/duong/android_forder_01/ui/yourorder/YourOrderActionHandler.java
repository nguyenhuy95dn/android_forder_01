package com.example.duong.android_forder_01.ui.yourorder;

import com.example.duong.android_forder_01.data.model.Orders;

public class YourOrderActionHandler {
    private YourOrderContract.Presenter mListener;

    public YourOrderActionHandler(YourOrderContract.Presenter listener) {
        mListener = listener;
    }

    public void cancelOrder(Orders orders) {
        if(mListener==null) return;
        mListener.cancelOrder(orders);
    }

    public void filter() {
        if(mListener==null) return;
        mListener.filter();
    }
}

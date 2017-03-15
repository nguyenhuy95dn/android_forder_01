package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.closeorder;

/**
 * Created by Duong on 3/10/2017.
 */
public class CloseOrderActionHandler {
    private CloseOrderContract.Presenter mListener;

    public CloseOrderActionHandler(CloseOrderContract.Presenter listener) {
        mListener = listener;
    }

    public void backToCheckOrder() {
        if (mListener == null) return;
        mListener.backToCheckOrder();
    }

    public void done() {
        if (mListener == null) return;
        mListener.done();
    }
}

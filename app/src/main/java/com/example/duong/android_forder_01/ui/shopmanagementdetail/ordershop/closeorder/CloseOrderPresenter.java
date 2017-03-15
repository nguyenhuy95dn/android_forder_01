package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.closeorder;

public class CloseOrderPresenter implements CloseOrderContract.Presenter {
    private CloseOrderContract.View mView;

    public CloseOrderPresenter(CloseOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void backToCheckOrder() {
        // todo back to check order screen
    }

    @Override
    public void done() {
        // todo send update order to server
    }
}

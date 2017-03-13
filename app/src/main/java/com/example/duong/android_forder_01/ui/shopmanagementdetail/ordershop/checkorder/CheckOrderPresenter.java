package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder;

import com.example.duong.android_forder_01.data.model.OrderDetail;

public class CheckOrderPresenter implements CheckOrderContract.Presenter {
    private CheckOrderContract.View mView;

    public CheckOrderPresenter(CheckOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void loadOrder(int shopId) {
        // todo load order from api
    }

    @Override
    public void checkAll() {
        // todo update checkbox status for item
    }

    @Override
    public void checkOne(OrderDetail orderDetail) {
        // todo update checkbox status for item
    }

    @Override
    public void acceptAll() {
        // todo update status for item
    }

    @Override
    public void acceptOne(OrderDetail orderDetail) {
        // todo update status for item
    }

    @Override
    public void rejectAll() {
        // todo update status for item
    }

    @Override
    public void rejectOne(OrderDetail orderDetail) {
        // todo update status for item
    }

    @Override
    public void continueCheckOrder() {
        // todo open close order screen
    }
}

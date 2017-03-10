package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop;

/**
 * Created by tri on 07/03/2017.
 */
public class OrderOfShopPresenter implements OrderOfShopContract.Presenter {
    private OrderOfShopContract.View mView;

    public OrderOfShopPresenter(OrderOfShopContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}

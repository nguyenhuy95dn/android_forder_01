package com.example.duong.android_forder_01.ui.yourorder;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Orders;

public class YourOrderPresenter implements YourOrderContract.Presenter {
    private YourOrderContract.View mHomeView;

    public YourOrderPresenter(@NonNull YourOrderContract.View homeView) {
        mHomeView = homeView;
        homeView.setPresenter(this);
    }

    @Override
    public void start() {
        mHomeView.start();
    }

    @Override
    public void cancelOrder(Orders orders) {
        // todo request cancel order
    }

    @Override
    public void filter() {
        // todo filter and reload order;
    }
}

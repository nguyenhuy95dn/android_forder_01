package com.example.duong.android_forder_01.ui.shopmanagementdetail.shopinformation;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Shop;

public class ShopInformationPresenter implements ShopInformationContract.Presenter {
    private ShopInformationContract.View mShopView;

    public ShopInformationPresenter(@NonNull ShopInformationContract.View shopView) {
        mShopView = shopView;
        mShopView.setPresenter(this);
    }

    @Override
    public void start() {
        mShopView.start();
    }

    @Override
    public void openOrderList(Shop shop) {
        //todo open order list activity
    }

    @Override
    public void openOrderHistory(Shop shop) {
        //todo open order history activity
    }

    @Override
    public void editOwner(Shop shop) {
        // todo show dialog change shop owner
    }

    @Override
    public void editInformation(Shop shop) {
        // todo open edit shop information activity
    }
}

package com.example.duong.android_forder_01.ui.home.shop;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Shop;

public class ShopPresenter implements ShopContract.Presenter {
    private ShopContract.View mShopView;

    public ShopPresenter(@NonNull ShopContract.View shopView) {
        mShopView = shopView;
        mShopView.setPresenter(this);
    }

    @Override
    public void start() {
        mShopView.start();
    }

    @Override
    public void openShopDetail(Shop shop) {
        if (shop == null) return;
        mShopView.openShopDetail(shop);
    }
}

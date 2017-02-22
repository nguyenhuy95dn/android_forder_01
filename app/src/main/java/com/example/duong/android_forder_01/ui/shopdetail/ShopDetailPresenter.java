package com.example.duong.android_forder_01.ui.shopdetail;

import android.support.annotation.NonNull;

public class ShopDetailPresenter implements ShopDetailContract.Presenter {
    private ShopDetailContract.View mShopDetailView;

    public ShopDetailPresenter(@NonNull ShopDetailContract.View shopDetailView) {
        mShopDetailView = shopDetailView;
        mShopDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        mShopDetailView.start();
    }
}

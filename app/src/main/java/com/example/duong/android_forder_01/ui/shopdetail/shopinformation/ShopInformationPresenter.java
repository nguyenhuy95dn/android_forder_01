package com.example.duong.android_forder_01.ui.shopdetail.shopinformation;

import android.support.annotation.NonNull;

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
}

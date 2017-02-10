package com.example.duong.android_forder_01.ui.home.shop;

import com.example.duong.android_forder_01.data.model.Shop;

public class ShopItemActionHandler {
    private ShopContract.Presenter mListener;

    public ShopItemActionHandler(ShopContract.Presenter listener) {
        mListener = listener;
    }

    public void itemClick(Shop shop) {
        mListener.openShopDetail(shop);
    }
}

package com.example.duong.android_forder_01.ui.shopmanagementdetail.shopinformation;

import com.example.duong.android_forder_01.data.model.Shop;

/**
 * Created by Duong on 4/4/2017.
 */
public class ShopInformationActionHandler {
    private ShopInformationContract.Presenter mListener;

    public ShopInformationActionHandler(ShopInformationContract.Presenter listener) {
        mListener = listener;
    }

    public void openOrderList(Shop shop) {
        if (mListener == null) return;
        mListener.openOrderList(shop);
    }

    public void openOrderHistory(Shop shop) {
        if (mListener == null) return;
        mListener.openOrderHistory(shop);
    }

    public void editOwner(Shop shop) {
        if (mListener == null) return;
        mListener.editOwner(shop);
    }

    public void editInformation(Shop shop) {
        if (mListener == null) return;
        mListener.editInformation(shop);
    }
}

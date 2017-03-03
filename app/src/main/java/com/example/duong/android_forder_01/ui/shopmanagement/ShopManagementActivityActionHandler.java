package com.example.duong.android_forder_01.ui.shopmanagement;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopDomain;
import com.example.duong.android_forder_01.data.model.ShopManagement;

/**
 * Created by tri on 02/03/2017.
 */
public class ShopManagementActivityActionHandler {
    private ShopManagementContract.Presenter mListener;

    public ShopManagementActivityActionHandler(ShopManagementContract.Presenter listener) {
        mListener = listener;
    }

    public void sendRequest(ShopDomain shopDomain, Shop shop) {
        if (mListener == null) return;
        mListener.sendRequest(shopDomain, shop);
    }

    public void openDetailShop(ShopManagement shopManagement) {
        if (mListener == null) return;
        mListener.openDetailShop(shopManagement);
    }
}

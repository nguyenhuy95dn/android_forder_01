package com.example.duong.android_forder_01.ui.shopmanagement;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.ShopDomain;
import com.example.duong.android_forder_01.data.model.ShopManagement;

/**
 * Created by tri on 02/03/2017.
 */
public class ShopManagementPresenter implements ShopManagementContract.Presenter {
    private ShopManagementContract.View mView;

    public ShopManagementPresenter(ShopManagementContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getShopByIdUser(int userId) {
        // TODO Call api get shop by Id
    }

    @Override
    public void sendRequest(ShopDomain shopDomain, Shop shop) {
        // TODO send request join shop into domain
    }

    @Override
    public void openDetailShop(ShopManagement shopManagement) {
        mView.showDetailShop(shopManagement);
    }
}

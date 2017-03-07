package com.example.duong.android_forder_01.ui.shopmanagementdetail;

/**
 * Created by tri on 06/03/2017.
 */
public class ShopManagementDetailPresenter implements ShopManagementDetailContract.Presenter {
    private ShopManagementDetailContract.View mView;

    public ShopManagementDetailPresenter(ShopManagementDetailContract.View shopDetailView) {
        mView = shopDetailView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}

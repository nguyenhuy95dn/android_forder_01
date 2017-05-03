package com.framgia.forder.screen.shopmanagement;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel implements ShopManagementContract.ViewModel {

    private ShopManagementContract.Presenter mPresenter;

    public ShopManagementViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onClickShopManagement() {
        //Todo Open Fragment Register Shop
    }
}

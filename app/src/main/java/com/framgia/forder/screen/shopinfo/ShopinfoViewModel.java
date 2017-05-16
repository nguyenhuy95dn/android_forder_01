package com.framgia.forder.screen.shopinfo;

/**
 * Exposes the data to be used in the Shopinfo screen.
 */

public class ShopinfoViewModel implements ShopinfoContract.ViewModel {

    private ShopinfoContract.Presenter mPresenter;

    public ShopinfoViewModel() {
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
    public void setPresenter(ShopinfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

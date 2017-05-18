package com.framgia.forder.screen.shopinfo.productshopinfo;

/**
 * Exposes the data to be used in the ProductShopInfo screen.
 */

public class ProductShopInfoViewModel implements ProductShopInfoContract.ViewModel {

    private ProductShopInfoContract.Presenter mPresenter;

    public ProductShopInfoViewModel() {
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
    public void setPresenter(ProductShopInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

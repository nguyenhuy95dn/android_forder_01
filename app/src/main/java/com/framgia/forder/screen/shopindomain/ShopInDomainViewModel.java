package com.framgia.forder.screen.shopindomain;

/**
 * Exposes the data to be used in the Shopindomain screen.
 */

public class ShopInDomainViewModel implements ShopInDomainContract.ViewModel {

    private ShopInDomainContract.Presenter mPresenter;

    public ShopInDomainViewModel() {
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
    public void setPresenter(ShopInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

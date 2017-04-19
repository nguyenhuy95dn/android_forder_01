package com.framgia.forder.screen.searchshop;

/**
 * Exposes the data to be used in the Searchshop screen.
 */

public class ShopSearchResultViewModel implements ShopSearchResultContract.ViewModel {

    private ShopSearchResultContract.Presenter mPresenter;

    public ShopSearchResultViewModel() {
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
    public void setPresenter(ShopSearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

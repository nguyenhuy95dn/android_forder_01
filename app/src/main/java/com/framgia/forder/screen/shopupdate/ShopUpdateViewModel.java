package com.framgia.forder.screen.shopupdate;

import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ShopUpdate screen.
 */

public class ShopUpdateViewModel implements ShopUpdateContract.ViewModel {

    private final Navigator mNavigator;
    private ShopUpdateContract.Presenter mPresenter;

    ShopUpdateViewModel(Navigator navigator) {
        mNavigator = navigator;
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
    public void setPresenter(ShopUpdateContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

package com.framgia.forder.screen.listProduct;

import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Productpage screen.
 */

public class ListProductViewModel implements ListProductContract.ViewModel {

    private ListProductContract.Presenter mPresenter;
    private Navigator mNavigator;

    public ListProductViewModel(Navigator navigator) {
        mNavigator= navigator;
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
    public void setPresenter(ListProductContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

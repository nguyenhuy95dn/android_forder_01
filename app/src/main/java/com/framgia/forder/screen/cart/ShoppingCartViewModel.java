package com.framgia.forder.screen.cart;

/**
 * Exposes the data to be used in the ShoppingCart screen.
 */

public class ShoppingCartViewModel implements ShoppingCartContract.ViewModel {

    private ShoppingCartContract.Presenter mPresenter;

    public ShoppingCartViewModel() {
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
    public void setPresenter(ShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

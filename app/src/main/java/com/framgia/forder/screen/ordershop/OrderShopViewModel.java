package com.framgia.forder.screen.ordershop;

/**
 * Exposes the data to be used in the OrderShop screen.
 */

public class OrderShopViewModel implements OrderShopContract.ViewModel {

    private OrderShopContract.Presenter mPresenter;

    public OrderShopViewModel() {
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
    public void setPresenter(OrderShopContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

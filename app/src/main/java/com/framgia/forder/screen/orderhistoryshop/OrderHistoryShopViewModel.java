package com.framgia.forder.screen.orderhistoryshop;

/**
 * Exposes the data to be used in the OrderHistoryShop screen.
 */

public class OrderHistoryShopViewModel implements OrderHistoryShopContract.ViewModel {

    private OrderHistoryShopContract.Presenter mPresenter;
    private final OrderHistoryPageAdapter mAdapter;

    OrderHistoryShopViewModel(OrderHistoryPageAdapter adapter) {
        mAdapter = adapter;
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
    public void setPresenter(OrderHistoryShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public OrderHistoryPageAdapter getAdapter() {
        return mAdapter;
    }
}

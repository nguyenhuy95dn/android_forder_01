package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

import com.framgia.forder.data.model.OrderHistory;

/**
 * Exposes the data to be used in the ListDoneOrders screen.
 */

public class ListDoneOrdersViewModel implements ListDoneOrdersContract.ViewModel {

    private ListDoneOrdersContract.Presenter mPresenter;
    private final OrderHistory mOrderHistory;

    ListDoneOrdersViewModel(OrderHistory orderHistory) {
        mOrderHistory = orderHistory;
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
    public void setPresenter(ListDoneOrdersContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getNameProduct() {
        return mOrderHistory.getNameProduct();
    }

    public String getTimeCreateOrder() {
        return mOrderHistory.getTimeOrderFormat();
    }

    public int getQuantity() {
        return mOrderHistory.getQuantity();
    }

    public String getPrice() {
        return mOrderHistory.getPriceFormat();
    }
}

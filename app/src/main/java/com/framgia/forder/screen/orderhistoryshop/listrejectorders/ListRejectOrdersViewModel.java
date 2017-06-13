package com.framgia.forder.screen.orderhistoryshop.listrejectorders;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ListRejectOrders screen.
 */

public class ListRejectOrdersViewModel implements ListRejectOrdersContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;
    private final OrderHistory mOrderHistory;
    private final ShopManagement mShopManagement;
    private final ListRejectOrdersAdapter mAdapter;
    private ListRejectOrdersContract.Presenter mPresenter;
    private final ObservableField<Integer> mProgressBarVisibilityListOrder;

    ListRejectOrdersViewModel(@NonNull Context context, Navigator navigator,
            ListRejectOrdersAdapter adapter, OrderHistory orderHistory,
            ShopManagement shopManagement) {
        this.mContext = context;
        mNavigator = navigator;
        mAdapter = adapter;
        mOrderHistory = orderHistory;
        mShopManagement = shopManagement;
        mProgressBarVisibilityListOrder = new ObservableField<>();
        mProgressBarVisibilityListOrder.set(View.GONE);
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
    public void setPresenter(ListRejectOrdersContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getListRejectOrders(mShopManagement.getShop().getId());
    }

    @Override
    public void onGetListRejectOrdersSuccess(List<OrderHistory> orderHistories) {
        mAdapter.upDateData(orderHistories);
    }

    @Override
    public void onGetListRejectOrdersError(Exception exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onShowProgressBar() {
        mProgressBarVisibilityListOrder.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressBar() {
        mProgressBarVisibilityListOrder.set(View.GONE);
    }

    public String getNameProduct() {
        return mOrderHistory.getNameProduct();
    }

    public String getTimeCreateOrder() {
        return mOrderHistory.getTimeOrderFormat();
    }

    public String getQuantity() {
        return String.valueOf(
                String.format(mContext.getString(R.string.quantity), mOrderHistory.getQuantity()));
    }

    public String getPrice() {
        return mOrderHistory.getPriceFormat();
    }

    public ListRejectOrdersAdapter getAdapter() {
        return mAdapter;
    }

    public ObservableField<Integer> getProgressBarVisibilityListOrder() {
        return mProgressBarVisibilityListOrder;
    }
}

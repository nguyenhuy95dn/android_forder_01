package com.framgia.forder.screen.ordershop;

import com.framgia.forder.R;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the OrderShop screen.
 */

public class OrderShopViewModel
        implements OrderShopContract.ViewModel, OrderShopAdapter.OrderManagementListener {

    private final Navigator mNavigator;
    private OrderShopContract.Presenter mPresenter;
    private final OrderShopAdapter mOrderShopAdapter;
    private final ShopManagement mShopManagement;

    public OrderShopViewModel(Navigator navigator, OrderShopAdapter orderShopAdapter,
            ShopManagement shopManagement) {
        mNavigator = navigator;
        mOrderShopAdapter = orderShopAdapter;
        mShopManagement = shopManagement;
        mOrderShopAdapter.setOrderManagementListener(this);
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
        mPresenter.getListOrderManagementShop(mShopManagement.getShop().getId());
    }

    @Override
    public void onGetListOrderManagementShopSuccess(List<Order> orders) {
        mOrderShopAdapter.updateData(orders);
    }

    @Override
    public void onGetListOrderManagementShopError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onAcceptOrRejectOrderManageSuccess() {
        mNavigator.showToast(R.string.order_successful);
        mOrderShopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAcceptOrRejectOrderManageError(Exception exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onOrderSuccess(List<Order> orders) {
        mNavigator.showToast(R.string.order_successful);
        mOrderShopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOrderError(Exception exception) {
        mNavigator.showToast(exception.getMessage());
    }

    public OrderShopAdapter getOrderShopAdapter() {
        return mOrderShopAdapter;
    }

    @Override
    public void onAcceptOrRejectOrderManager(
            OrderManagement acceptAndRejectOrdermanagementRequest) {
        mPresenter.acceptAndRejectOrder(mShopManagement.getShop().getId(),
                acceptAndRejectOrdermanagementRequest);
    }
}

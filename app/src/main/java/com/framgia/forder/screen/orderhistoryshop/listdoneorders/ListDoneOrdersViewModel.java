package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ListDoneOrders screen.
 */

public class ListDoneOrdersViewModel extends BaseObservable
        implements ListDoneOrdersContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;
    private ListDoneOrdersContract.Presenter mPresenter;
    private final OrderHistory mOrderHistory;
    private final ListDoneOrderAdapter mAdapter;
    private final ShopManagement mShopManagement;
    private final ObservableField<Integer> mProgressBarVisibilityListOrder =
            new ObservableField<>();
    private boolean mIsHaveData;

    ListDoneOrdersViewModel(@NonNull Context context, Navigator navigator,
            OrderHistory orderHistory, ListDoneOrderAdapter adapter,
            ShopManagement shopManagement) {
        mContext = context;
        mNavigator = navigator;
        mAdapter = adapter;
        mOrderHistory = orderHistory;
        mShopManagement = shopManagement;
        mProgressBarVisibilityListOrder.set(View.GONE);
        setHaveData(true);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListDoneOrder(mShopManagement.getShop().getId());
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

    public String getQuantity() {
        return String.valueOf(
                String.format(mContext.getString(R.string.quantity), mOrderHistory.getQuantity()));
    }

    public String getPrice() {
        return mOrderHistory.getPriceFormat();
    }

    @Override
    public void onGetListDoneOrdersSuccess(List<OrderHistory> orderHistories) {
        if (orderHistories.size() == 0) {
            setHaveData(false);
            return;
        }
        setHaveData(true);
        mAdapter.upDateData(orderHistories);
    }

    @Override
    public void onGetListDoneOrdersError(Exception exception) {
        mNavigator.showToast(exception.getMessage());
        setHaveData(false);
    }

    @Override
    public void onShowProgressBar() {
        mProgressBarVisibilityListOrder.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressBar() {
        mProgressBarVisibilityListOrder.set(View.GONE);
    }

    public ObservableField<Integer> getProgressBarVisibilityListOrder() {
        return mProgressBarVisibilityListOrder;
    }

    public ListDoneOrderAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isHaveData() {
        return mIsHaveData;
    }

    public void setHaveData(boolean haveData) {
        mIsHaveData = haveData;
        notifyPropertyChanged(BR.haveData);
    }
}

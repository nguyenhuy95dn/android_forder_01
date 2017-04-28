package com.framgia.forder.screen.ordermanagement;

import com.framgia.forder.R;
import com.framgia.forder.screen.ordermanagement.checkorder.CheckOrderFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the OrderManagement screen.
 */

public class OrderManagementViewModel implements OrderManagementContract.ViewModel {

    private OrderManagementContract.Presenter mPresenter;
    private Navigator mNavigator;

    OrderManagementViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        setFragment();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(OrderManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void setFragment() {
        mNavigator.setFragment(R.id.content_order_management, CheckOrderFragment.newInstance(),
                false, Navigator.NONE, "CheckOrderFragment");
    }
}

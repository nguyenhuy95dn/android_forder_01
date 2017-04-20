package com.framgia.forder.screen.listshop;

import android.content.Context;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ShopFragment screen.
 */

public class ListShopViewModel implements ListShopContract.ViewModel {

    private ListShopContract.Presenter mPresenter;
    private Navigator mNavigator;

    public ListShopViewModel(Context context, ShopAdapter shopAdapter, Navigator navigator) {
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
    public void setPresenter(ListShopContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

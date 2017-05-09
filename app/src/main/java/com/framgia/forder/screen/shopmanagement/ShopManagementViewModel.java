package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel implements ShopManagementContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ShopManagemantFragment";

    private final Navigator mNavigator;
    private final ListShopManagementAdapter mListShopManagementAdapter;
    private ShopManagementContract.Presenter mPresenter;

    public ShopManagementViewModel(Navigator navigator, ListShopManagementAdapter adapter) {
        mNavigator = navigator;
        mListShopManagementAdapter = adapter;
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
    public void setPresenter(ShopManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onClickShopManagement() {
        //Todo Open Fragment Register Shop
    }

    @Override
    public void onGetListShopManagementError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListShopManagementSuccess(List<ShopManagement> shopManagements) {
        mListShopManagementAdapter.updateData(shopManagements);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        //Todo Show Fragment Domain in Shop
    }

    public ListShopManagementAdapter getListShopManagementAdapter() {
        return mListShopManagementAdapter;
    }
}

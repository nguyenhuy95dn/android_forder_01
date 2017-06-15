package com.framgia.forder.screen.listshop;

import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.shopDetail.ShopDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Exposes the data to be used in the ShopFragment screen.
 */

public class ListShopViewModel extends Observable implements ListShopContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private ListShopContract.Presenter mPresenter;
    private final ListShopAdapter mAdapter;
    private final Navigator mNavigator;

    ListShopViewModel(Navigator navigator, ListShopAdapter adapter) {
        mNavigator = navigator;
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
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

    @Override
    public void onGetListAllShopError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void onGetListAllShopSuccess(List<Shop> shops) {
        mAdapter.updateData(shops);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Shop)) {
            return;
        }
        Shop shop = (Shop) item;
        mNavigator.goNextChildFragment(R.id.layout_content, ShopDetailFragment.newInstance(shop),
                true, Navigator.RIGHT_LEFT, "ShopDetailFragment");
    }

    public ListShopAdapter getAdapter() {
        return mAdapter;
    }
}

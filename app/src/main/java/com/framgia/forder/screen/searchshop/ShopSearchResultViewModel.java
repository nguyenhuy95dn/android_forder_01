package com.framgia.forder.screen.searchshop;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import java.util.List;

/**
 * Exposes the data to be used in the Searchshop screen.
 */

public class ShopSearchResultViewModel implements ShopSearchResultContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private ShopSearchResultContract.Presenter mPresenter;
    private ShopAdapter mAdapter;

    public ShopSearchResultViewModel(ShopAdapter adapter) {
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
    public void setPresenter(ShopSearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ShopAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchSuccess(List<Shop> shops) {
        mAdapter.updateData(shops);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        // TODO view detail shop
    }
}

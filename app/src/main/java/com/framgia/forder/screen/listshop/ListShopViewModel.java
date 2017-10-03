package com.framgia.forder.screen.listshop;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.shopDetail.ShopDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ShopFragment screen.
 */

public class ListShopViewModel extends BaseObservable implements ListShopContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ListShopViewModel";

    private ListShopContract.Presenter mPresenter;
    private final ListShopAdapter mAdapter;
    private final Navigator mNavigator;
    private boolean mIsProgressbarVisibleListAllShop;
    private boolean mIsHaveData;

    ListShopViewModel(Navigator navigator, ListShopAdapter adapter) {
        mNavigator = navigator;
        mAdapter = adapter;
        mAdapter.setItemClickListener(this);
        setProgressbarVisibleListAllShop(false);
        setHaveData(true);
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
        setHaveData(false);
    }

    @Override
    public void onGetListAllShopSuccess(List<Shop> shops) {
        if (shops.size() == 0) {
            setHaveData(false);
            return;
        }
        setHaveData(true);
        mAdapter.updateData(shops);
    }

    @Override
    public void onShowProgressBar() {
        setProgressbarVisibleListAllShop(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressbarVisibleListAllShop(false);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Shop)) {
            return;
        }
        Shop shop = (Shop) item;
        mNavigator.goNextChildFragment(R.id.layout_content, ShopDetailFragment.newInstance(shop),
                true, Navigator.LEFT_RIGHT, TAG);
    }

    public ListShopAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isProgressbarVisibleListAllShop() {
        return mIsProgressbarVisibleListAllShop;
    }

    public void setProgressbarVisibleListAllShop(boolean progressbarVisibleListAllShop) {
        mIsProgressbarVisibleListAllShop = progressbarVisibleListAllShop;
        notifyPropertyChanged(BR.progressbarVisibleListAllShop);
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

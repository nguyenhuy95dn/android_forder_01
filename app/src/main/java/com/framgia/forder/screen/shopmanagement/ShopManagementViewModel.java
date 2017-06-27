package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.createshop.CreateshopFragment;
import com.framgia.forder.screen.shopinfo.ShopInformationPageContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel implements ShopManagementContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private static final String TAG = "ShopDetailFragment";

    private final Navigator mNavigator;
    private final ListShopManagementAdapter mListShopManagementAdapter;
    private ShopManagementContract.Presenter mPresenter;

    ShopManagementViewModel(Navigator navigator, ListShopManagementAdapter adapter) {
        mNavigator = navigator;
        mListShopManagementAdapter = adapter;
        mListShopManagementAdapter.setItemClickListener(this);
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
        mNavigator.goNextChildFragment(R.id.layout_content, CreateshopFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
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
    public void onRequestShopInDomainSuccess() {
        mPresenter.getListShopManagement();
    }

    @Override
    public void onRequestShopInDomainError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onCancelJoinDomainSuccess() {
        mPresenter.getListShopManagement();
    }

    @Override
    public void onCancelJoinDomainError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof ShopManagement)) {
            return;
        }
        ShopManagement shopManagement = (ShopManagement) item;
        mNavigator.goNextChildFragment(R.id.layout_content,
                ShopInformationPageContainerFragment.newInstance(shopManagement), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public ListShopManagementAdapter getListShopManagementAdapter() {
        return mListShopManagementAdapter;
    }
}

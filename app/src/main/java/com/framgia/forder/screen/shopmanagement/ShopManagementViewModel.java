package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.createshop.CreateshopFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ShopManagement screen.
 */

public class ShopManagementViewModel implements ShopManagementContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>,
        ListShopManagementAdapter.ShopDomainManagementListener {

    private final Navigator mNavigator;
    private final ListShopManagementAdapter mListShopManagementAdapter;
    private ShopManagementContract.Presenter mPresenter;

    public ShopManagementViewModel(Navigator navigator, ListShopManagementAdapter adapter) {
        mNavigator = navigator;
        mListShopManagementAdapter = adapter;
        mListShopManagementAdapter.setShopDomainManagementListener(this);
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
        mNavigator.goNextChildFragment(R.id.layout_content, CreateshopFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, "CreateshopFragment");
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
        //Todo Show Fragment Domain in Shop
    }

    public ListShopManagementAdapter getListShopManagementAdapter() {
        return mListShopManagementAdapter;
    }

    @Override
    public void onRequestJoinDomain(ApplyShopToDomainRequest applyShopToDomainRequest) {
        mPresenter.onRequestJoinDomain(applyShopToDomainRequest);
    }

    @Override
    public void onCancleJoinDomain(LeaveShopToDomainRequest leaveShopToDomainRequest) {
        mPresenter.onCancelJoinDomain(leaveShopToDomainRequest);
    }
}

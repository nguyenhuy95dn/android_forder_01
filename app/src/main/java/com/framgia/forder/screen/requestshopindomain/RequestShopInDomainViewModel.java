package com.framgia.forder.screen.requestshopindomain;

import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Requestshopindomain screen.
 */

public class RequestShopInDomainViewModel implements RequestShopInDomainContract.ViewModel,
        RequestShopInDomainAdapter.RequestShopInDomainListener {
    private static final String STATUS_APPROVED = "approved";
    private static final String STATUS_REJECTED = "rejected";

    private RequestShopInDomainContract.Presenter mPresenter;
    private final Navigator mNavigator;
    private final RequestShopInDomainAdapter mAdapter;
    private final DomainManagement mDomainManagement;
    private final DialogManager mDialogManager;

    RequestShopInDomainViewModel(Navigator navigator, RequestShopInDomainAdapter adapter,
            DomainManagement domainManagement, DialogManager dialogManager) {
        mNavigator = navigator;
        mAdapter = adapter;
        mDomainManagement = domainManagement;
        mDialogManager = dialogManager;
        mAdapter.setListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListRequestShop(mDomainManagement.getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(RequestShopInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListRequestShopSuccess(List<ShopRequestResponse.ShopContain> shops) {
        mAdapter.updateData(shops);
    }

    @Override
    public void onGetMessageError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onRequestShopSuccess() {
        mPresenter.getListRequestShop(mDomainManagement.getId());
    }

    @Override
    public void showProgressBarDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void hideProgressBarDialog() {
        mDialogManager.dismissProgressDialog();
    }

    public RequestShopInDomainAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onClickAcceptShop(ShopRequestResponse.ShopContain shopContain) {
        mPresenter.requestToAcceptRejectShopToDomain(mDomainManagement.getId(),
                shopContain.getShopId(), STATUS_APPROVED);
    }

    @Override
    public void onClickRejectShop(ShopRequestResponse.ShopContain shopContain) {
        mPresenter.requestToAcceptRejectShopToDomain(mDomainManagement.getId(),
                shopContain.getShopId(), STATUS_REJECTED);
    }
}

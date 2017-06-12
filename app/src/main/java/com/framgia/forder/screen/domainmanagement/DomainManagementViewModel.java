package com.framgia.forder.screen.domainmanagement;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Domainmanagement screen.
 */

public class DomainManagementViewModel extends BaseObservable
        implements DomainManagementContract.ViewModel, DomainManagementListener {
    private static final String TAG = "DomainManagementViewModel";

    private DomainManagementContract.Presenter mPresenter;
    private final DomainManagementAdapter mDomainManagementAdapter;
    private final Navigator mNavigator;

    DomainManagementViewModel(DomainManagementAdapter domainManagementAdapter,
            Navigator navigator) {
        mDomainManagementAdapter = domainManagementAdapter;
        mNavigator = navigator;
        domainManagementAdapter.setDomainManagementListener(this);
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
    public void setPresenter(DomainManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public DomainManagementAdapter getListDomainManagement() {
        return mDomainManagementAdapter;
    }

    @Override
    public void onGetListDomainManagementSuccess(List<DomainManagement> domainManagementList) {
        mDomainManagementAdapter.updateData(domainManagementList);
    }

    @Override
    public void onGetListDomainManagementError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onGetListUserInDomain(int domainId) {
        //Todo dev later
    }

    @Override
    public void onGetListShopInDomain(int domainId) {
        //Todo dev later
    }

    @Override
    public void onLeaveDomain(int domainId) {
        //Todo dev later
    }

    public void onClickAddDomain() {
        //Todo dev later
    }
}

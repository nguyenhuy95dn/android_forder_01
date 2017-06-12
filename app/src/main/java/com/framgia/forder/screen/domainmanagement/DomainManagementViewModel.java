package com.framgia.forder.screen.domainmanagement;

import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.RegisterDomain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainFragment;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainListener;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Domainmanagement screen.
 */

public class DomainManagementViewModel extends BaseObservable
        implements DomainManagementContract.ViewModel, DomainManagementListener, AddDomainListener {
    private static final String TAG = "DomainManagementViewModel";

    private DomainManagementContract.Presenter mPresenter;
    private final DomainManagementAdapter mDomainManagementAdapter;
    private final Navigator mNavigator;

    DomainManagementViewModel(DomainManagementAdapter domainManagementAdapter, Navigator navigator,
            AddDomainFragment addDomainFragment) {
        mDomainManagementAdapter = domainManagementAdapter;
        mNavigator = navigator;
        domainManagementAdapter.setDomainManagementListener(this);
        addDomainFragment.setAddDomainListener(this);
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
    public void onRegisterDomainSuccess() {
        mNavigator.showToast(R.string.add_domain_success);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onRegisterDomainError(BaseException error) {
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
        mNavigator.showAddDomainDialog("AddDomainFragment", this);
    }

    @Override
    public void onRequestRegisterDomain(String nameDomain, String status) {
        RegisterDomainRequest registerDomainRequest = new RegisterDomainRequest();
        RegisterDomain domain = new RegisterDomain();
        domain.setName(nameDomain);
        domain.setStatus(status);
        registerDomainRequest.setDomain(domain);
        mPresenter.registerDomain(registerDomainRequest);
    }
}

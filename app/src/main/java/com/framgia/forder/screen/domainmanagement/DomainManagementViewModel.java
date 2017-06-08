package com.framgia.forder.screen.domainmanagement;

/**
 * Exposes the data to be used in the Domainmanagement screen.
 */

public class DomainManagementViewModel implements DomainManagementContract.ViewModel {

    private DomainManagementContract.Presenter mPresenter;

    public DomainManagementViewModel() {
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
}

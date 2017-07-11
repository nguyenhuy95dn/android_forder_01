package com.framgia.forder.screen.adduserindomain;

/**
 * Exposes the data to be used in the Adduserindomain screen.
 */

public class AddUserInDomainViewModel implements AddUserInDomainContract.ViewModel {

    private AddUserInDomainContract.Presenter mPresenter;

    public AddUserInDomainViewModel() {
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
    public void setPresenter(AddUserInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

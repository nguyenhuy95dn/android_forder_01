package com.framgia.forder.screen.userindomain;

/**
 * Exposes the data to be used in the Userindomain screen.
 */

public class UserInDomainViewModel implements UserInDomainContract.ViewModel {

    private UserInDomainContract.Presenter mPresenter;

    UserInDomainViewModel() {
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
    public void setPresenter(UserInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

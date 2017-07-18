package com.framgia.forder.screen.addmanagershop;

/**
 * Exposes the data to be used in the Addmanagershop screen.
 */

public class AddManagerShopViewModel implements AddManagerShopContract.ViewModel {

    private AddManagerShopContract.Presenter mPresenter;

    public AddManagerShopViewModel() {
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
    public void setPresenter(AddManagerShopContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

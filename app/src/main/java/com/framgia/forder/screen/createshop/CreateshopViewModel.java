package com.framgia.forder.screen.createshop;

/**
 * Exposes the data to be used in the Createshop screen.
 */

public class CreateshopViewModel implements CreateshopContract.ViewModel {

    private CreateshopContract.Presenter mPresenter;

    public CreateshopViewModel() {
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
    public void setPresenter(CreateshopContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

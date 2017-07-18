package com.framgia.forder.screen.requestshopindomain;

/**
 * Exposes the data to be used in the Requestshopindomain screen.
 */

public class RequestShopInDomainViewModel implements RequestShopInDomainContract.ViewModel {

    private RequestShopInDomainContract.Presenter mPresenter;

    public RequestShopInDomainViewModel() {
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
    public void setPresenter(RequestShopInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

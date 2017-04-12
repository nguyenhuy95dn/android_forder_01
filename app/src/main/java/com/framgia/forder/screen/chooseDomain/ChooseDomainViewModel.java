package com.framgia.forder.screen.chooseDomain;

/**
 * Exposes the data to be used in the ChooseDomain screen.
 */

public class ChooseDomainViewModel implements ChooseDomainContract.ViewModel {

    private ChooseDomainContract.Presenter mPresenter;

    public ChooseDomainViewModel() {
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
    public void setPresenter(ChooseDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

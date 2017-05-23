package com.framgia.forder.screen.managerdetail;

/**
 * Exposes the data to be used in the ManagerDetail screen.
 */

public class ManagerDetailViewModel implements ManagerDetailContract.ViewModel {

    private ManagerDetailContract.Presenter mPresenter;

    public ManagerDetailViewModel() {
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
    public void setPresenter(ManagerDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

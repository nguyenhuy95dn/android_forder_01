package com.framgia.forder.screen.main;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainViewModel implements MainContract.ViewModel {

    private MainContract.Presenter mPresenter;

    public MainViewModel() {
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
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

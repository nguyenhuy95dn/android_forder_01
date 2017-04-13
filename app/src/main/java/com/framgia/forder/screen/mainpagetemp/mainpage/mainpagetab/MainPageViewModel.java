package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageViewModel implements MainPageContract.ViewModel {

    private MainPageContract.Presenter mPresenter;

    public MainPageViewModel() {
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
    public void setPresenter(MainPageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

package com.framgia.forder.screen.mainpagetemp.mainpage;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageContainerViewModel implements MainPageContainerContract.ViewModel {

    private MainPageContainerContract.Presenter mPresenter;

    public MainPageContainerViewModel() {
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
    public void setPresenter(MainPageContainerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

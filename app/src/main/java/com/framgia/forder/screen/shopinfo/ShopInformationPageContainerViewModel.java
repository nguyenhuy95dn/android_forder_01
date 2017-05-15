package com.framgia.forder.screen.shopinfo;

/**
 * Exposes the data to be used in the shopinfo screen.
 */

public class ShopInformationPageContainerViewModel
        implements ShopInformationPageContainerContract.ViewModel {

    private ShopInformationPageContainerContract.Presenter mPresenter;
    private final ShopInformationPageAdapter mAdapter;

    public ShopInformationPageContainerViewModel(ShopInformationPageAdapter adapter) {
        mAdapter = adapter;
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
    public void setPresenter(ShopInformationPageContainerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ShopInformationPageAdapter getAdapter() {
        return mAdapter;
    }
}

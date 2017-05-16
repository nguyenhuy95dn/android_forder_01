package com.framgia.forder.screen.shopinfo;

/**
 * Listens to user actions from the UI ({@link ShopinfoFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopinfoPresenter implements ShopinfoContract.Presenter {
    private static final String TAG = ShopinfoPresenter.class.getName();

    private final ShopinfoContract.ViewModel mViewModel;

    ShopinfoPresenter(ShopinfoContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

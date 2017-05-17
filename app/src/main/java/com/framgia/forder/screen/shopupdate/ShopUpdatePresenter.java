package com.framgia.forder.screen.shopupdate;

/**
 * Listens to user actions from the UI ({@link ShopUpdateFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ShopUpdatePresenter implements ShopUpdateContract.Presenter {
    private static final String TAG = ShopUpdatePresenter.class.getName();

    private final ShopUpdateContract.ViewModel mViewModel;

    ShopUpdatePresenter(ShopUpdateContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

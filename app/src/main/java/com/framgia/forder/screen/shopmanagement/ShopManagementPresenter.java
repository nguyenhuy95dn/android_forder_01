package com.framgia.forder.screen.shopmanagement;

/**
 * Listens to user actions from the UI ({@link ShopManagementFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShopManagementPresenter implements ShopManagementContract.Presenter {
    private static final String TAG = ShopManagementPresenter.class.getName();

    private final ShopManagementContract.ViewModel mViewModel;

    ShopManagementPresenter(ShopManagementContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

package com.framgia.forder.screen.addmanagershop;

/**
 * Listens to user actions from the UI ({@link AddManagerShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class AddManagerShopPresenter implements AddManagerShopContract.Presenter {
    private static final String TAG = AddManagerShopPresenter.class.getName();

    private final AddManagerShopContract.ViewModel mViewModel;

    AddManagerShopPresenter(AddManagerShopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

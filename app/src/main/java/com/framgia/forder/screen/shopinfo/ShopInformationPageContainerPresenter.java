package com.framgia.forder.screen.shopinfo;

/**
 * Listens to user actions from the UI ({@link ShopInformationPageContainerFragment}), retrieves the
 * data
 * and updates
 * the UI as required.
 */
final class ShopInformationPageContainerPresenter
        implements ShopInformationPageContainerContract.Presenter {
    private static final String TAG = ShopInformationPageContainerPresenter.class.getName();

    private final ShopInformationPageContainerContract.ViewModel mViewModel;

    ShopInformationPageContainerPresenter(
            ShopInformationPageContainerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

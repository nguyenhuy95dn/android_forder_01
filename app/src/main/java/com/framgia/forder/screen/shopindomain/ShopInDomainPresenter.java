package com.framgia.forder.screen.shopindomain;

/**
 * Listens to user actions from the UI ({@link ShopInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShopInDomainPresenter implements ShopInDomainContract.Presenter {
    private static final String TAG = ShopInDomainPresenter.class.getName();

    private final ShopInDomainContract.ViewModel mViewModel;

    public ShopInDomainPresenter(ShopInDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

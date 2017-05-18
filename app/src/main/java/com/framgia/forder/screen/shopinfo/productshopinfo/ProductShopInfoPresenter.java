package com.framgia.forder.screen.shopinfo.productshopinfo;

/**
 * Listens to user actions from the UI ({@link ProductShopInfoFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ProductShopInfoPresenter implements ProductShopInfoContract.Presenter {
    private static final String TAG = ProductShopInfoPresenter.class.getName();

    private final ProductShopInfoContract.ViewModel mViewModel;

    ProductShopInfoPresenter(ProductShopInfoContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

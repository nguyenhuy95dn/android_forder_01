package com.framgia.forder.screen.cart;

/**
 * Listens to user actions from the UI ({@link ShoppingCartFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ShoppingCartPresenter implements ShoppingCartContract.Presenter {
    private static final String TAG = ShoppingCartPresenter.class.getName();

    private final ShoppingCartContract.ViewModel mViewModel;

    public ShoppingCartPresenter(ShoppingCartContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

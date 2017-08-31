package com.framgia.forder.screen.addtocart;

/**
 * Listens to user actions from the UI ({@link AddToCartFragment}), retrieves the data and updates
 * the UI as required.
 */
final class AddToCartPresenter implements AddToCartContract.Presenter {
    private static final String TAG = AddToCartPresenter.class.getName();

    private final AddToCartContract.ViewModel mViewModel;

    AddToCartPresenter(AddToCartContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

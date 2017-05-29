package com.framgia.forder.screen.updateProduct;

/**
 * Listens to user actions from the UI ({@link UpdateProductFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UpdateProductPresenter implements UpdateProductContract.Presenter {
    private static final String TAG = UpdateProductPresenter.class.getName();

    private final UpdateProductContract.ViewModel mViewModel;

    UpdateProductPresenter(UpdateProductContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

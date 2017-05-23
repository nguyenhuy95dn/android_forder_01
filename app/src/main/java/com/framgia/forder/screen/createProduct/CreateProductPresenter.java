package com.framgia.forder.screen.createProduct;

/**
 * Listens to user actions from the UI ({@link CreateProductFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class CreateProductPresenter implements CreateProductContract.Presenter {
    private static final String TAG = CreateProductPresenter.class.getName();

    private final CreateProductContract.ViewModel mViewModel;

    CreateProductPresenter(CreateProductContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

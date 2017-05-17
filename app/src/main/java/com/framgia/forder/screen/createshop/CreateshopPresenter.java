package com.framgia.forder.screen.createshop;

/**
 * Listens to user actions from the UI ({@link CreateshopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class CreateshopPresenter implements CreateshopContract.Presenter {
    private static final String TAG = CreateshopPresenter.class.getName();

    private final CreateshopContract.ViewModel mViewModel;

    CreateshopPresenter(CreateshopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

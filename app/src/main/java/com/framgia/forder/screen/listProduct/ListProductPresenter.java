package com.framgia.forder.screen.listProduct;

/**
 * Listens to user actions from the UI ({@link ListProductFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ListProductPresenter implements ListProductContract.Presenter {
    private static final String TAG = ListProductPresenter.class.getName();

    private final ListProductContract.ViewModel mViewModel;

    public ListProductPresenter(ListProductContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

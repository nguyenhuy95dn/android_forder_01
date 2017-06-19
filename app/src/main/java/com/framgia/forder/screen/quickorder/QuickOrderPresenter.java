package com.framgia.forder.screen.quickorder;

/**
 * Listens to user actions from the UI ({@link QuickOrderFragment}), retrieves the data and updates
 * the UI as required.
 */
final class QuickOrderPresenter implements QuickOrderContract.Presenter {
    private static final String TAG = QuickOrderPresenter.class.getName();

    private final QuickOrderContract.ViewModel mViewModel;

    QuickOrderPresenter(QuickOrderContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

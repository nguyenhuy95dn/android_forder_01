package com.framgia.forder.screen.domainmanagement.editdomain;

/**
 * Listens to user actions from the UI ({@link EditdomainFragment}), retrieves the data and updates
 * the UI as required.
 */
final class EditdomainPresenter implements EditdomainContract.Presenter {
    private static final String TAG = EditdomainPresenter.class.getName();

    private final EditdomainContract.ViewModel mViewModel;

    EditdomainPresenter(EditdomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

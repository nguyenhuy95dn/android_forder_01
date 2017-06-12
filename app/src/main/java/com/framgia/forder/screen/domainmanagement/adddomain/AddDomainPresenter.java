package com.framgia.forder.screen.domainmanagement.adddomain;

/**
 * Listens to user actions from the UI ({@link AddDomainFragment}), retrieves the data and updates
 * the UI as required.
 */
final class AddDomainPresenter implements AddDomainContract.Presenter {
    private static final String TAG = AddDomainPresenter.class.getName();

    private final AddDomainContract.ViewModel mViewModel;

    AddDomainPresenter(AddDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

package com.framgia.forder.screen.domainmanagement;

/**
 * Listens to user actions from the UI ({@link DomainManagementFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class DomainManagementPresenter implements DomainManagementContract.Presenter {
    private static final String TAG = DomainManagementPresenter.class.getName();

    private final DomainManagementContract.ViewModel mViewModel;

    DomainManagementPresenter(DomainManagementContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

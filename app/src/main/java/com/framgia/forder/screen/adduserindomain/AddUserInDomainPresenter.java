package com.framgia.forder.screen.adduserindomain;

/**
 * Listens to user actions from the UI ({@link AddUserInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class AddUserInDomainPresenter implements AddUserInDomainContract.Presenter {
    private static final String TAG = AddUserInDomainPresenter.class.getName();

    private final AddUserInDomainContract.ViewModel mViewModel;

    AddUserInDomainPresenter(AddUserInDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

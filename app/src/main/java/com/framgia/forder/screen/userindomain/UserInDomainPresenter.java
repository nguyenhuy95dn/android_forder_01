package com.framgia.forder.screen.userindomain;

/**
 * Listens to user actions from the UI ({@link UserInDomainFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UserInDomainPresenter implements UserInDomainContract.Presenter {
    private static final String TAG = UserInDomainPresenter.class.getName();

    private final UserInDomainContract.ViewModel mViewModel;

    UserInDomainPresenter(UserInDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

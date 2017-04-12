package com.framgia.forder.screen.chooseDomain;

/**
 * Listens to user actions from the UI ({@link ChooseDomainActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ChooseDomainPresenter implements ChooseDomainContract.Presenter {
    private static final String TAG = ChooseDomainPresenter.class.getName();

    private final ChooseDomainContract.ViewModel mViewModel;

    public ChooseDomainPresenter(ChooseDomainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

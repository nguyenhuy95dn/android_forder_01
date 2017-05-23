package com.framgia.forder.screen.managerdetail;

/**
 * Listens to user actions from the UI ({@link ManagerDetailFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ManagerDetailPresenter implements ManagerDetailContract.Presenter {
    private static final String TAG = ManagerDetailPresenter.class.getName();

    private final ManagerDetailContract.ViewModel mViewModel;

     ManagerDetailPresenter(ManagerDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

/**
 * Listens to user actions from the UI ({@link ListDoneOrdersFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ListDoneOrdersPresenter implements ListDoneOrdersContract.Presenter {
    private static final String TAG = ListDoneOrdersPresenter.class.getName();

    private final ListDoneOrdersContract.ViewModel mViewModel;

    ListDoneOrdersPresenter(ListDoneOrdersContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

package com.framgia.forder.screen.ordershop;

/**
 * Listens to user actions from the UI ({@link OrderShopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class OrderShopPresenter implements OrderShopContract.Presenter {
    private static final String TAG = OrderShopPresenter.class.getName();

    private final OrderShopContract.ViewModel mViewModel;

    OrderShopPresenter(OrderShopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

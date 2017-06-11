package com.framgia.forder.screen.orderhistoryshop;

/**
 * Listens to user actions from the UI ({@link OrderHistoryShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class OrderHistoryShopPresenter implements OrderHistoryShopContract.Presenter {
    private static final String TAG = OrderHistoryShopPresenter.class.getName();

    private final OrderHistoryShopContract.ViewModel mViewModel;

    OrderHistoryShopPresenter(OrderHistoryShopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

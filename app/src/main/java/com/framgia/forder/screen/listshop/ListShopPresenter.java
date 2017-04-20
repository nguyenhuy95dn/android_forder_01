package com.framgia.forder.screen.listshop;

/**
 * Listens to user actions from the UI ({@link ListShopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class ListShopPresenter implements ListShopContract.Presenter {
    private static final String TAG = ListShopPresenter.class.getName();

    private final ListShopContract.ViewModel mViewModel;

    public ListShopPresenter(ListShopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void getListAllShop() {

    }
}

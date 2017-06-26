package com.framgia.forder.screen.managerinshop;

/**
 * Listens to user actions from the UI ({@link ManagerInShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ManagerInShopPresenter implements ManagerInShopContract.Presenter {

    private static final String TAG = ManagerInShopPresenter.class.getName();

    private final ManagerInShopContract.ViewModel mViewModel;

    ManagerInShopPresenter(ManagerInShopContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

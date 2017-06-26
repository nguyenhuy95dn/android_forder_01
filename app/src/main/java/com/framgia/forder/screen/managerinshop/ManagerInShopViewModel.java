package com.framgia.forder.screen.managerinshop;

/**
 * Exposes the data to be used in the Managerinshop screen.
 */

public class ManagerInShopViewModel implements ManagerInShopContract.ViewModel {

    private ManagerInShopContract.Presenter mPresenter;

    private final ManagerInShopAdapter mManagerInShopAdapter;

    ManagerInShopViewModel(ManagerInShopAdapter managerInShopAdapter) {
        mManagerInShopAdapter = managerInShopAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ManagerInShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ManagerInShopAdapter getOwnerInShopAdater() {
        return mManagerInShopAdapter;
    }
}

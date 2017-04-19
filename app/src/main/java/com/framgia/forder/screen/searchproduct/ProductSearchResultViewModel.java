package com.framgia.forder.screen.searchproduct;

/**
 * Exposes the data to be used in the Searchproduct screen.
 */

public class ProductSearchResultViewModel implements ProductSearchResultContract.ViewModel {

    private ProductSearchResultContract.Presenter mPresenter;

    public ProductSearchResultViewModel() {
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
    public void setPresenter(ProductSearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

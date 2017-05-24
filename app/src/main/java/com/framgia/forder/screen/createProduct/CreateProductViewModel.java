package com.framgia.forder.screen.createProduct;

/**
 * Exposes the data to be used in the Createproduct screen.
 */

public class CreateProductViewModel implements CreateProductContract.ViewModel {

    private CreateProductContract.Presenter mPresenter;

    CreateProductViewModel() {
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
    public void setPresenter(CreateProductContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

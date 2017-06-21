package com.framgia.forder.screen.mainpage.shop;

/**
 * Created by ths on 21/06/2017.
 */

final class ShopPagePresenter implements ShopPageContract.Presenter {

    private ShopPageContract.ViewModel mViewModel;

    ShopPagePresenter(ShopPageContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}

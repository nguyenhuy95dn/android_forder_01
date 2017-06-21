package com.framgia.forder.screen.mainpage.shop;

import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * Created by ths on 21/06/2017.
 */

public interface ShopPageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<ShopPageContract.Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}

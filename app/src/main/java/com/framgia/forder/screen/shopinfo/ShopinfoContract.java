package com.framgia.forder.screen.shopinfo;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopinfoContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListManagerOfShopSuccess(List<User> users);

        void onGetListManagerOfShopError(BaseException exception);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListManagerOfShop(int shopId);
    }
}

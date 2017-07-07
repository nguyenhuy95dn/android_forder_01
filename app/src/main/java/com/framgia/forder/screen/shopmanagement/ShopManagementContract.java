package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopManagementContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onShowMessageError(BaseException exception);

        void onGetListShopManagementSuccess(List<ShopManagement> shopManagements);

        void onShowProgressBar();

        void onHideProgressBar();

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onChangeStatusShopSuccess();

        void onReLoadData();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListShopManagement();

        void requestChangeStatusShop(int shopId, String status);
    }
}

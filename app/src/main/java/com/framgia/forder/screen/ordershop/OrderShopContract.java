package com.framgia.forder.screen.ordershop;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface OrderShopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListOrderManagementShopSuccess(List<Order> orders);

        void onGetListOrderManagementShopError(BaseException exception);

        void onAcceptOrRejectOrderManageSuccess();

        void onAcceptOrRejectOrderManageError(Exception exception);

        void onReLoadData();

        void onShowProgressBarListOrder();

        void onHideProgressBarListOrder();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListOrderManagementShop(int shopId);

        void acceptAndRejectOrder(int shopId, OrderManagement acceptAndRejectInOrder);

    }
}

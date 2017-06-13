package com.framgia.forder.screen.orderhistoryshop.listrejectorders;

import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListRejectOrdersContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListRejectOrdersSuccess(List<OrderHistory> orderHistories);

        void onGetListRejectOrdersError(Exception exception);

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListRejectOrders(int shopId);
    }
}

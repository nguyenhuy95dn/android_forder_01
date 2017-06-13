package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListDoneOrdersContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListDoneOrdersSuccess(List<OrderHistory> orderHistories);

        void onGetListDoneOrdersError(Exception exception);

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListDoneOrder(int shopId);
    }
}

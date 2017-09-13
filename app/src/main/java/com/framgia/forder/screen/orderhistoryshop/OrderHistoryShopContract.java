package com.framgia.forder.screen.orderhistoryshop;

import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface OrderHistoryShopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onFilterListDoneOrderSuccess(List<OrderHistory> listDoneOrders);

        void onFilterListRejectOrdeSuccess(List<OrderHistory> listRejectedOrder);

        void onFilterListOrderError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListOrdersShopFilter(int shopId, String startDate, String endDate);
    }
}

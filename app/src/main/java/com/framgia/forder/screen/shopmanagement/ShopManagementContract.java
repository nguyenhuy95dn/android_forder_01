package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
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

        void onGetListShopManagementError(BaseException exception);

        void onGetListShopManagementSuccess(List<ShopManagement> shopManagements);

        void onRequestShopInDomainSuccess();

        void onRequestShopInDomainError(BaseException exception);

        void onCancelJoinDomainSuccess();

        void onCancelJoinDomainError(BaseException exception);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListShopManagement();

        void onRequestJoinDomain(ApplyShopToDomainRequest applyShopToDomainRequest);

        void onCancelJoinDomain(LeaveShopToDomainRequest leaveShopToDomainRequest);
    }
}

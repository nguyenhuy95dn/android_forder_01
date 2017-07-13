package com.framgia.forder.screen.shopinfo;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
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

        void onApplyToDomainSuccess();

        void onLeaveToDomainSuccess();

        void onApplyOrLeaveToDomainError(BaseException exception);

        void onShowProgressBarDialog();

        void onHideProgressBarDialog();

        void onGetListDomainSuccess(List<DomainToRequestShopResponse.DomainToRequest> domains);

        void onShowProgressBarListManager();

        void onHideProgressBarListManager();

        void onShowProgressBarListDomain();

        void onHideProgressBarListDomain();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListManagerOfShop(int shopId);

        void onApplyToDomain(ApplyShopToDomainRequest applyShopToDomainRequest);

        void onLeaveToDomain(int domainId, int shopId, boolean leaveDomain);

        void getListDomainToRequestShop(int shopId);
    }
}

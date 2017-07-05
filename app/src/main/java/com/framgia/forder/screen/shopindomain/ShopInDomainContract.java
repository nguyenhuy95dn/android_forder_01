package com.framgia.forder.screen.shopindomain;

import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopInDomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListShopInDomainSuccess(List<ShopInDomain> shops, int userId);

        void onGetListShopInDomainError(BaseException error);

        void ondeleteShopInDomainSuccess();

        void ondeleteShopInDomainError(BaseException error);

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListShopInDomain(int domainId);

        void deleteShop(int domainId, int shopId);
    }
}

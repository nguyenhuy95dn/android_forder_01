package com.framgia.forder.screen.requestshopindomain;

import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface RequestShopInDomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListRequestShopSuccess(List<ShopRequestResponse.ShopContain> shops);

        void onGetMessageError(BaseException error);

        void onRequestShopSuccess();

        void showProgressBarDialog();

        void hideProgressBarDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListRequestShop(int domainId);

        void requestToAcceptRejectShopToDomain(int domainId, int shopId, String status);
    }
}

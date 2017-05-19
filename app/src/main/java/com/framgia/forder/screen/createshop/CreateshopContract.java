package com.framgia.forder.screen.createshop;

import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CreateshopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onInputNameError();

        void onInputDescriptionError();

        void onRequestRegisterShopSuccess();

        void onRequestRegisterShopError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        boolean validateDataInput(String name, String description);

        void onRequestRegisterShop(RegisterShopRequest registerShopRequest);
    }
}

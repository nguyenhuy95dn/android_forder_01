package com.framgia.forder.screen.shopupdate;

import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopUpdateContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onInputNameError();

        void onInputDescriptionError();

        void onUpdateShopSuccess();

        void onUpdateShopError(BaseException error);

        void setImageCover(String imageCover);

        void setImageAvatar(String imageAvatar);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        boolean validateDataInput(String name, String description);

        void onUpdateShop(UpdateShopRequest updateShopRequest);
    }
}

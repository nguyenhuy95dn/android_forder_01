package com.framgia.forder.screen.shopDetail;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShopDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListAllProductShopSuccess(List<Product> products);

        void onGetListAllProductShopError(BaseException error);

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListAllProductShop(int shopId);
    }
}

package com.framgia.forder.screen.shopinfo.productshopinfo;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductShopInfoContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListAllProductShopInformationError(BaseException exception);

        void onGetListAllProductShopInformationSuccess(List<Product> products);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListAllProductShopInformation(int shopId);
    }
}

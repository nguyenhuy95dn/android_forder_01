package com.framgia.forder.screen.addtocart;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AddToCartContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetTotalPriceSuccess(Double totalPrice);

        void onGetTotalPriceError(BaseException error);

        void onAddToCartSuccess();

        void onAddToCartError(Throwable e);

        void onDeleteProductInCartSuccess();

        void onDeleteProductInCartError(Throwable e);

        void onDownQuantitySuccess();

        void onDownQuantityError(Throwable e);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getTotalPrice();

        void addToCart(Product product);

        void downQuantity(Product product);

        void deleteProduct(Product product);
    }
}

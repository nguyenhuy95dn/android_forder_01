package com.framgia.forder.screen.cart;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ShoppingCartContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListCartError(BaseException exception);

        void onGetListCartSuccess(List<Cart> carts);

        void onUpQuantityError(BaseException exception);

        void onUpQuantitySuccess();

        void onDownQuantityError(BaseException exception);

        void onDownQuantitySuccess();

        void onDeleteProductError(BaseException exception);

        void onDeleteProductSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void orderItem(Cart cart);

        void getListCart();

        void upQuantity(CartItem cartItem);

        void downQuantity(CartItem cartItem);

        void deleteProduct(CartItem cartItem);
    }
}

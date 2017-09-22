package com.framgia.forder.screen.productinshop;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductInShopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onAddToCartSuccess(Product product);

        void onAddToCartError(Throwable e);

        void onGetListProductSuccess(List<Product> products);

        void onGetMessageError(BaseException error);

        void onOrderProductSuccess();

        void onGetListCartSuccess(List<Cart> carts, Product product);

        void onGetListCartError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProductInShop(int shopId);

        void orderProduct(OrderRequest orderRequest);

        void getListCart(Product product);
    }
}

package com.framgia.forder.screen.searchproduct;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductSearchResultContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSearchSuccess(List<Product> products);

        void onAddToCartSuccess();

        void onOrderProductError(BaseException e);

        void onOrderProductSuccess();

        void onAddToCartError(Throwable e);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void orderProduct(OrderRequest orderRequest);
    }
}

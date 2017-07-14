package com.framgia.forder.screen.listproductbycategory;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListProductByCategoryContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListProductSuccess(List<Product> products);

        void onGetMessageError(BaseException error);

        void onShowProgressBar();

        void onHideProgressBar();

        void onAddToCartSuccess();

        void onAddToCartError(Throwable e);

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onOrderProductSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProductByCategory(int categoryId);

        void orderProduct(OrderRequest orderRequest);
    }
}

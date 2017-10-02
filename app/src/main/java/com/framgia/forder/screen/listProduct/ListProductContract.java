package com.framgia.forder.screen.listProduct;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListProductContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListAllProductError(BaseException exception);

        void onGetListAllProductSuccess(List<Product> products);

        void onAddToCartError(Throwable throwable);

        void onAddToCartSuccess(Product product);

        void onOrderProductSuccess();

        void onOrderProductError(BaseException error);

        void onShowProgressBar();

        void onHideProgressBar();

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onGetListCartSuccess(List<Cart> carts, Product product);

        void onGetListCartError(BaseException error);

        void onGetCategoriesSuccess(List<Category> categories);

        void onGetCategoriesError(BaseException error);

        void reloadData();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListAllProduct();

        void orderProduct(OrderRequest orderRequest);

        void getListCart(Product product);

        void getListCategory();

        void getListProductByFillter(int categoryId, String priceSort, int priceFrom, int priceTo);

        void getListAllProductByFillter(String priceSort, int priceFrom, int priceTo);
    }
}

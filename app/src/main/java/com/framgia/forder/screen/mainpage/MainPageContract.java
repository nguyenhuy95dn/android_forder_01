package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainPageContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListProductError(BaseException exception);

        void onGetListProductSuccess(List<Product> products);

        void onGetListShopError(BaseException exception);

        void onGetListShopSuccess(List<Shop> shops);

        void onAddToCartError(Throwable exception);

        void onAddToCartSuccess(Product product);

        void onGetListCategorySuccess(List<Category> categories);

        void onGetListCategoryError(BaseException exception);

        void onShowProgressbarProduct();

        void onHideProgressbarProduct();

        void onShowProgressbarShop();

        void onHideProgressbarShop();

        void onShowProgressbarCategory();

        void onHideProgressbarCategory();

        void onOrderProductSuccess();

        void onOrderProductError(BaseException error);

        void onShowProgressDialog();

        void onHideProgressDialog();

        void reloadData();

        void onGetListCartSuccess(List<Cart> carts, Product product);

        void onGetListCartError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProduct();

        void getListShop();

        void getListCategory();

        void orderProduct(OrderRequest orderRequest);

        void getListCart(Product product);
    }
}

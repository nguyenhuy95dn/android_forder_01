package com.framgia.forder.screen.productdetail;

import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ProductDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListProductShopSuccess(List<Product> products);

        void onShowMessageError(BaseException exception);

        void onGetListCommentInProductSusscess(List<Comment> commentsList);

        void onAddToCartSuccess();

        void onOrderNowSuccess();

        void onCommentSuccess();

        void onShowProgressBarComment();

        void onHideProgressBarComment();

        void onShowProgressBarListProduct();

        void onHideProgressBarListProduct();

        void onShowProgressBarListComment();

        void onHideProgressBarListComment();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void addToCart(Product product);

        void getListProductInShop(int shopId);

        void getListCommentInProduct(int productId);

        void sendComment(CommentRequest request);

        void orderNow(OrderRequest orderRequest);
    }
}

package com.framgia.forder.screen.productdetail;

import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.User;
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

        void onAddToCartSuccess(Product product);

        void onOrderNowSuccess();

        void onCommentSuccess();

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onShowProgressBarListProduct();

        void onHideProgressBarListProduct();

        void onShowProgressBarListComment();

        void onHideProgressBarListComment();

        void onReLoadData();

        void onGetUser(User user);

        void onDeleteCommentSuccess();

        void onAddToCartError(Throwable e);

        void onGetListCartSuccess(List<Cart> carts, Product product);

        void onGetListCartError(BaseException error);
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

        void deleteCommentInProduct(int commentId);

        void getListCart(Product product);
    }
}

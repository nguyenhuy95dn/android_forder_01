package com.framgia.forder.screen.productdetail;

import android.content.DialogInterface;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.RegisterSendComment;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.mainpage.ordercart.BaseOrderCartViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.productdetail.adapter.CommentAdapter;
import com.framgia.forder.screen.productdetail.adapter.ProductShopAdapter;
import com.framgia.forder.screen.quickorder.QuickOrderListener;
import com.framgia.forder.utils.Utils;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Detailproduct screen.
 */

public class ProductDetailViewModel extends BaseOrderCartViewModel
        implements ProductDetailContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener, CommentAdapter.DeleteCommentListener {

    private static final String TAG = "ProductDetailViewModel";

    private ProductDetailContract.Presenter mPresenter;
    private final Product mProduct;
    private final Navigator mNavigator;
    private final ProductShopAdapter mProductShopAdapter;
    private final CommentAdapter mCommentInProductAdapter;
    private String mComment;
    private User mUser;
    private boolean mIsProgressBarListProductVisible;
    private boolean mIsProgressBarListCommentVisible;
    private final DialogManager mDialogManager;
    private final LoadCartListener mLoadCartListener;

    ProductDetailViewModel(Product product, ProductShopAdapter productShopAdapter,
            CommentAdapter commentInProductAdapter, Navigator navigator,
            DialogManager dialogManager, LoadCartListener loadCartListener) {
        mProduct = product;
        mNavigator = navigator;
        mProductShopAdapter = productShopAdapter;
        mDialogManager = dialogManager;
        mLoadCartListener = loadCartListener;
        mCommentInProductAdapter = commentInProductAdapter;
        mProductShopAdapter.setItemClickListener(this);
        mCommentInProductAdapter.setDeleteCommentListener(this);
        setProgressBarListCommentVisible(false);
        setProgressBarListProductVisible(false);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListProductInShop(mProduct.getShopId());
        onGetListComment();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListProductShopSuccess(List<Product> products) {
        mProductShopAdapter.updateData(products);
    }

    @Override
    public void onShowMessageError(BaseException exception) {
        mNavigator.showToastCustom(exception.getMessage());
    }

    @Override
    public void onGetListCommentInProductSusscess(List<Comment> comments) {
        mCommentInProductAdapter.updateData(comments, mUser);
    }

    @Override
    public void onAddToCartSuccess(Product product) {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
        mPresenter.getListCart(product);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (!(item instanceof Product)) {
            return;
        }
        Product product = (Product) item;
        mNavigator.goNextChildFragment(R.id.layout_content,
                ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT, TAG);
    }

    @Override
    public void onOrderNowSuccess() {
        mNavigator.showToastCustomActivity(R.string.order_successful);
    }

    @Override
    public void onCommentSuccess() {
        onReLoadData();
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onShowProgressBarListProduct() {
        setProgressBarListProductVisible(true);
    }

    @Override
    public void onHideProgressBarListProduct() {
        setProgressBarListProductVisible(false);
    }

    @Override
    public void onShowProgressBarListComment() {
        setProgressBarListCommentVisible(true);
    }

    @Override
    public void onHideProgressBarListComment() {
        setProgressBarListCommentVisible(false);
    }

    @Override
    public void onReLoadData() {
        onGetListComment();
    }

    @Override
    public void onGetUser(User user) {
        mUser = user;
    }

    @Override
    public void onDeleteCommentSuccess() {
        onReLoadData();
    }

    @Override
    public void onAddToCartError(Throwable e) {
        mNavigator.showToastCustom(e.getMessage());
    }

    @Override
    public void onGetListCartSuccess(List<Cart> carts, Product product) {
        mNavigator.showAddToCartDialog("AddToCartFragment", product, getTotalProductInCart(carts),
                getQuantityProduct(carts, product));
    }

    @Override
    public void onGetListCartError(BaseException error) {
        Log.e(TAG, "onGetListCartError: ", error);
    }

    @Override
    public void onRequestOrderNow(Product product, double totalPrice, int quantity, String note) {
        OrderRequest request = new OrderRequest();
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        cartItem.setPrice(totalPrice);
        cartItem.setQuantity(quantity);
        cartItem.setNotes(note);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Cart cart = new Cart();
        cart.setShopId(product.getShop().getId());
        cart.setTotal((int) totalPrice);
        cart.setCartItemList(cartItems);

        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        request.setCartList(carts);
        mPresenter.orderNow(request);
    }

    @Override
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
    }

    @Override
    public void onQuickOrder(Product product) {
        mNavigator.showQuickOrderDialog(TAG, product, this);
    }

    private void onGetListComment() {
        mPresenter.getListCommentInProduct(mProduct.getId());
    }

    public String getNameProduct() {
        return mProduct.getName();
    }

    public String getDescription() {
        return mProduct.getDescription();
    }

    public String getProductPrice() {
        return mProduct.getFormatPrice();
    }

    public String getOrderTime() {
        return mProduct.getFormatStartHour() + "-" + mProduct.getFormatEndHour();
    }

    public String getProductImage() {
        if (mProduct.getCollectionImage() != null
                && mProduct.getCollectionImage().getImage() != null) {
            return mProduct.getCollectionImage().getImage().getUrl();
        }
        return "";
    }

    public String getShopImage() {
        if (mProduct.getShop() != null
                && mProduct.getShop().getCollectionAvatar() != null
                && mProduct.getShop().getCollectionAvatar().getImage() != null) {
            return mProduct.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mProduct.getShop() != null) {
            return mProduct.getShop().getName();
        }
        return "";
    }

    public String getDescriptionShop() {
        if (mProduct.getShop() != null) {
            return mProduct.getShop().getDescription();
        }
        return "";
    }

    public void onClickSeeAllProduct() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickSendComment() {
        if (TextUtils.isEmpty(mComment)) {
            return;
        }
        CommentRequest commentRequest = new CommentRequest();
        RegisterSendComment sendComment = new RegisterSendComment();
        commentRequest.setProductId(mProduct.getId());
        sendComment.setComment(mComment);
        commentRequest.setComment(sendComment);
        mPresenter.sendComment(commentRequest);
        setComment("");
        notifyPropertyChanged(BR.comment);
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_to_cart:
                onAddToCart(mProduct);
                return true;
            case R.id.order_now:
                onQuickOrder(mProduct);
                return true;
            default:
        }
        return false;
    }

    public ProductShopAdapter getProductShopAdapter() {
        return mProductShopAdapter;
    }

    public CommentAdapter getCommentAdapter() {
        return mCommentInProductAdapter;
    }

    @Bindable
    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
        notifyPropertyChanged(BR.comment);
    }

    @Bindable
    public boolean isProgressBarListProductVisible() {
        return mIsProgressBarListProductVisible;
    }

    @Bindable
    public boolean isProgressBarListCommentVisible() {
        return mIsProgressBarListCommentVisible;
    }

    private void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
    }

    private void setProgressBarListCommentVisible(boolean progressBarListCommentVisible) {
        mIsProgressBarListCommentVisible = progressBarListCommentVisible;
        notifyPropertyChanged(BR.progressBarListCommentVisible);
    }

    public boolean isProductTimeOut() {
        return Utils.DateTimeUntils.isProductTimeOut(mProduct.getFormatStartHour(),
                mProduct.getFormatEndHour());
    }

    @Override
    public void onDeleteComment(final int commentId) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_delete_domain,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteCommentInProduct(commentId);
                    }
                });
        mDialogManager.show();
    }
}

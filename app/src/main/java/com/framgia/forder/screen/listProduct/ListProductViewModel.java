package com.framgia.forder.screen.listProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.adapter.ListProductAdapter;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.quickorder.QuickOrderListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Productpage screen.
 */

public class ListProductViewModel extends BaseObservable implements ListProductContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener {
    private static final String TAG = "ListProductFragment";

    private final Navigator mNavigator;
    private ListProductContract.Presenter mPresenter;
    private final ListProductAdapter mListProductAdapter;
    private boolean mIsProgressBarListProductVisible;
    private final DialogManager mDialogManager;
    private final LoadCartListener mLoadCartListener;
    private int mProductIncart;
    private int mTotalProductInCart;

    ListProductViewModel(ListProductAdapter listProductAdapter, Navigator navigator,
            DialogManager dialogManager, LoadCartListener loadCartListener) {

        mListProductAdapter = listProductAdapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mLoadCartListener = loadCartListener;
        mListProductAdapter.setItemClickListener(this);
        mListProductAdapter.setOrderListener(this);
        setProgressBarListProductVisible(false);
        //Todo edit later
        mProductIncart = 0;
        mTotalProductInCart = 0;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListAllProduct();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ListProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllProductError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListAllProductSuccess(List<Product> products) {
        mListProductAdapter.updateData(products);
    }

    @Override
    public void onAddToCartError(Throwable throwable) {
        Log.d(TAG, "onAddToCartError", throwable);
    }

    @Override
    public void onAddToCartSuccess() {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
    }

    @Override
    public void onOrderProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.order_successful);
    }

    @Override
    public void onOrderProductError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onShowProgressBar() {
        setProgressBarListProductVisible(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressBarListProductVisible(false);
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
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
        //Todo edit later
        mNavigator.showAddToCartDialog("AddToCartFragment", product, mProductIncart,
                mTotalProductInCart);
    }

    @Override
    public void onQuickOrder(Product product) {
        mNavigator.showQuickOrderDialog("QuickOrderFragment", product, this);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof Product) {
            Product product = (Product) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                    "ProductDetailFragment");
        }
    }

    public ListProductAdapter getListProductAdapter() {
        return mListProductAdapter;
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

        mPresenter.orderProduct(request);
    }

    @Bindable
    public boolean isProgressBarListProductVisible() {
        return mIsProgressBarListProductVisible;
    }

    public void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
    }
}

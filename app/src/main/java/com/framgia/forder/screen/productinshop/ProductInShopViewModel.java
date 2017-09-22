package com.framgia.forder.screen.productinshop;

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
import com.framgia.forder.screen.mainpage.ordercart.BaseOrderCartViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.quickorder.QuickOrderListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Productinshop screen.
 */

public class ProductInShopViewModel extends BaseOrderCartViewModel
        implements ProductInShopContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener {
    private static final String TAG = "ProductInShopViewModel";
    private final Navigator mNavigator;
    private final ListProductAdapter mListProductAdapter;
    private final DialogManager mDialogManager;
    private final LoadCartListener mLoadCartListener;
    private final int mShopId;
    private ProductInShopContract.Presenter mPresenter;
    private boolean mIsProgressBarListProductVisible;

    ProductInShopViewModel(ListProductAdapter listProductAdapter, Navigator navigator,
            DialogManager dialogManager, LoadCartListener loadCartListener, int shopId) {
        mListProductAdapter = listProductAdapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mLoadCartListener = loadCartListener;
        mShopId = shopId;
        mListProductAdapter.setItemClickListener(this);
        mListProductAdapter.setOrderListener(this);
    }

    @Override
    public void setPresenter(ProductInShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListProductInShop(mShopId);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void onGetListProductSuccess(List<Product> products) {
        mListProductAdapter.updateData(products);
    }

    @Override
    public void onGetMessageError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onAddToCartSuccess(Product product) {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
        mPresenter.getListCart(product);
    }

    @Override
    public void onAddToCartError(Throwable e) {

    }

    @Override
    public void onOrderProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.order_successful);
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

    @Bindable
    public boolean isProgressBarListProductVisible() {
        return mIsProgressBarListProductVisible;
    }

    private void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
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

    @Override
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
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
}

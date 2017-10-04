package com.framgia.forder.screen.searchproduct;

import android.databinding.Bindable;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.mainpage.ordercart.BaseOrderCartViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.quickorder.QuickOrderListener;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Searchproduct screen.
 */

public class ProductSearchResultViewModel extends BaseOrderCartViewModel
        implements ProductSearchResultContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener {

    private static final String TAG = "ProductSearchResult";

    private ProductSearchResultContract.Presenter mPresenter;
    private final ProductAdapter mAdapter;
    private final Navigator mNavigator;
    private final LoadCartListener mLoadCartListener;
    private boolean mIsHaveData;

    ProductSearchResultViewModel(ProductAdapter adapter, Navigator navigator,
            LoadCartListener loadCartListener) {
        mAdapter = adapter;
        mNavigator = navigator;
        mLoadCartListener = loadCartListener;
        mAdapter.setOrderListener(this);
        mAdapter.setItemClickListener(this);
        setHaveData(true);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ProductSearchResultContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ProductAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchSuccess(List<Product> products) {
        if (products.size() == 0) {
            setHaveData(false);
            return;
        }
        setHaveData(true);
        mAdapter.updateData(products);
    }

    @Override
    public void onOrderProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.order_successful);
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
    public void onOrderProductError(BaseException e) {
        mNavigator.showToastCustom(e.getMessage());
    }

    @Override
    public void onQuickOrder(Product product) {
        mNavigator.showQuickOrderDialog("QuickOrderFragment", product, this);
    }

    @Override
    public void onAddToCart(Product product) {
        if (product == null) {
            return;
        }
        mPresenter.addToCart(product);
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

    @Override
    public void onAddToCartSuccess(Product product) {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
        mPresenter.getListCart(product);
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
    public boolean isHaveData() {
        return mIsHaveData;
    }

    public void setHaveData(boolean haveData) {
        mIsHaveData = haveData;
        notifyPropertyChanged(BR.haveData);
    }
}

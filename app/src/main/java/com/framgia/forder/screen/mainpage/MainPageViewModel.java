package com.framgia.forder.screen.mainpage;

import android.content.Context;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.listproductbycategory.ListProductByCategoryFragment;
import com.framgia.forder.screen.listshop.ListShopFragment;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.screen.mainpage.category.CategoryAdapter;
import com.framgia.forder.screen.mainpage.ordercart.BaseOrderCartViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopPageAdapter;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.quickorder.QuickOrderListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageViewModel extends BaseOrderCartViewModel implements MainPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener {

    private static final String TAG = "ListProductFragment";

    private final Context mContext;
    private final Navigator mNavigator;
    private MainPageContract.Presenter mPresenter;
    private final ProductAdapter mProductAdapter;
    private final CategoryAdapter mCategoryAdapter;
    private boolean mIsProgressBarVisibleShop;
    private boolean mIsProgressBarVisibleCategory;
    private boolean mIsProgressBarVisibleProduct;
    private final ShopPageAdapter mShopPageAdapter;
    private final DialogManager mDialogManager;
    private final int mPageLimit = 6;
    private final LoadCartListener mLoadCartListener;

    MainPageViewModel(@NonNull Context context, ProductAdapter productAdapter, Navigator navigator,
            CategoryAdapter categoryAdapter, ShopPageAdapter shopPageAdapter,
            DialogManager dialogManager, LoadCartListener loadCartListener) {
        this.mContext = context;
        mProductAdapter = productAdapter;
        mProductAdapter.setOrderListener(this);
        mProductAdapter.setItemClickListener(this);
        mNavigator = navigator;
        mCategoryAdapter = categoryAdapter;
        mCategoryAdapter.setItemClickListener(this);
        mDialogManager = dialogManager;
        mLoadCartListener = loadCartListener;
        setProgressBarVisibleShop(false);
        setProgressBarVisibleCategory(false);
        setProgressBarVisibleProduct(false);
        mShopPageAdapter = shopPageAdapter;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListShop();
        mPresenter.getListCategory();
        mPresenter.getListProduct();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(MainPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        if (item instanceof Product) {
            Product product = (Product) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT, TAG);
        }

        if (item instanceof Category) {
            Category category = (Category) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ListProductByCategoryFragment.newInstance(category.getId()), true,
                    Navigator.RIGHT_LEFT, TAG);
        }
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

    @Override
    public void onGetListProductError(BaseException exception) {
        Log.e(TAG, "onGetListShopError: ", exception);
    }

    @Override
    public void onGetListProductSuccess(List<Product> products) {
        mProductAdapter.updateData(products);
    }

    @Override
    public void onGetListShopError(BaseException exception) {
        Log.e(TAG, "onGetListShopError: ", exception);
    }

    @Override
    public void onGetListShopSuccess(List<Shop> shops) {
        mShopPageAdapter.updateShop(shops);
    }

    @Override
    public void onAddToCartError(Throwable exception) {
        Log.e(TAG, "onGetListShopError: ", exception);
    }

    @Override
    public void onAddToCartSuccess(Product product) {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
        mPresenter.getListCart(product);
    }

    @Override
    public void onGetListCategorySuccess(List<Category> categories) {
        mCategoryAdapter.updateData(categories);
    }

    @Override
    public void onGetListCategoryError(BaseException exception) {
        Log.e(TAG, "onGetListCartError: ", exception);
    }

    @Override
    public void onShowProgressbarProduct() {
        setProgressBarVisibleProduct(true);
    }

    @Override
    public void onHideProgressbarProduct() {
        setProgressBarVisibleProduct(false);
    }

    @Override
    public void onShowProgressbarShop() {
        setProgressBarVisibleShop(true);
    }

    @Override
    public void onHideProgressbarShop() {
        setProgressBarVisibleShop(false);
    }

    @Override
    public void onShowProgressbarCategory() {
        setProgressBarVisibleCategory(true);
    }

    @Override
    public void onHideProgressbarCategory() {
        setProgressBarVisibleCategory(false);
    }

    @Override
    public void onOrderProductSuccess() {
        mNavigator.showToastCustomActivity(R.string.order_successful);
    }

    @Override
    public void onOrderProductError(BaseException error) {
        Log.e(TAG, "onGetListCartError: ", error);
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
    public void reloadData() {
        mPresenter.getListShop();
        mPresenter.getListCategory();
        mPresenter.getListProduct();
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

    public void onSeeMoreShopClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListShopFragment.newInstance(), true,
                Navigator.LEFT_RIGHT, TAG);
    }

    public void onSeeMoreProductClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.LEFT_RIGHT, TAG);
    }

    public ProductAdapter getProductAdapter() {
        return mProductAdapter;
    }

    public CategoryAdapter getCategoryAdapter() {
        return mCategoryAdapter;
    }

    @Bindable
    public boolean isProgressBarVisibleShop() {
        return mIsProgressBarVisibleShop;
    }

    private void setProgressBarVisibleShop(boolean progressBarVisibleShop) {
        mIsProgressBarVisibleShop = progressBarVisibleShop;
        notifyPropertyChanged(BR.progressBarVisibleShop);
    }

    @Bindable
    public boolean isProgressBarVisibleCategory() {
        return mIsProgressBarVisibleCategory;
    }

    private void setProgressBarVisibleCategory(boolean progressBarVisibleCategory) {
        mIsProgressBarVisibleCategory = progressBarVisibleCategory;
        notifyPropertyChanged(BR.progressBarVisibleCategory);
    }

    @Bindable
    public boolean isProgressBarVisibleProduct() {
        return mIsProgressBarVisibleProduct;
    }

    private void setProgressBarVisibleProduct(boolean progressBarVisibleProduct) {
        mIsProgressBarVisibleProduct = progressBarVisibleProduct;
        notifyPropertyChanged(BR.progressBarVisibleProduct);
    }

    public ShopPageAdapter getShopPageAdapter() {
        return mShopPageAdapter;
    }

    public int getPageLimit() {
        return mPageLimit;
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
}

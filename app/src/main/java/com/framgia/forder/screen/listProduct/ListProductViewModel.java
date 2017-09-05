package com.framgia.forder.screen.listProduct;

import android.databinding.Bindable;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.CartItem;
import com.framgia.forder.data.model.Category;
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
 * Exposes the data to be used in the Productpage screen.
 */

public class ListProductViewModel extends BaseOrderCartViewModel
        implements ListProductContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener,
        QuickOrderListener {
    private static final String TAG = "ListProductFragment";

    private final List<Category> mCategories;
    private final Navigator mNavigator;
    private ListProductContract.Presenter mPresenter;
    private final ListProductAdapter mListProductAdapter;
    private boolean mIsProgressBarListProductVisible;
    private final DialogManager mDialogManager;
    private final LoadCartListener mLoadCartListener;
    private final ArrayAdapter<String> mAdapterPrice;
    private final ArrayAdapter<String> mAdapterCategory;
    private final ArrayAdapter<String> mAdapterSortBy;

    ListProductViewModel(ListProductAdapter listProductAdapter, Navigator navigator,
            DialogManager dialogManager, LoadCartListener loadCartListener,
            ArrayAdapter<String> adapterPrice, ArrayAdapter<String> adapterCategory,
            ArrayAdapter<String> adapterSort) {
        mListProductAdapter = listProductAdapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mLoadCartListener = loadCartListener;
        mAdapterPrice = adapterPrice;
        mAdapterCategory = adapterCategory;
        mAdapterSortBy = adapterSort;
        mListProductAdapter.setItemClickListener(this);
        mListProductAdapter.setOrderListener(this);
        setProgressBarListProductVisible(false);
        mCategories = new ArrayList<>();
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
    public void onAddToCartSuccess(Product product) {
        mNavigator.showToastCustomActivity(R.string.add_to_cart_success);
        mLoadCartListener.onReloadCart();
        mPresenter.getListCart(product);
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
    public void onGetListCartSuccess(List<Cart> carts, Product product) {
        mNavigator.showAddToCartDialog("AddToCartFragment", product, getTotalProductInCart(carts),
                getQuantityProduct(carts, product));
    }

    @Override
    public void onGetListCartError(BaseException error) {
        Log.e(TAG, "onGetListCartError: ", error);
    }

    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {
        if (categories == null) {
            return;
        }
        mAdapterCategory.clear();
        mCategories.clear();
        mCategories.addAll(categories);
        mAdapterCategory.add(mListProductAdapter.getContext().getString(R.string.all_category));
        for (Category category : categories) {
            mAdapterCategory.add(category.getName());
        }
        mAdapterCategory.notifyDataSetChanged();
    }

    @Override
    public void onGetCategoriesError(BaseException error) {
        Log.e(TAG, "onGetCategoriesError: ", error);
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

    private void setProgressBarListProductVisible(boolean progressBarListProductVisible) {
        mIsProgressBarListProductVisible = progressBarListProductVisible;
        notifyPropertyChanged(BR.progressBarListProductVisible);
    }

    public ArrayAdapter<String> getAdapterFrom() {
        return mAdapterPrice;
    }

    public ArrayAdapter<String> getAdapterTo() {
        return mAdapterPrice;
    }

    public ArrayAdapter<String> getAdapterCategory() {
        return mAdapterCategory;
    }

    public ArrayAdapter<String> getAdapterSortBy() {
        return mAdapterSortBy;
    }

    public void onClickFillter() {
        //Todo edit later
    }
}

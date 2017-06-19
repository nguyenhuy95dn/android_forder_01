package com.framgia.forder.screen.searchproduct;

import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Searchproduct screen.
 */

public class ProductSearchResultViewModel extends BaseObservable
        implements ProductSearchResultContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private ProductSearchResultContract.Presenter mPresenter;
    private ProductSearchResultAdapter mAdapter;
    private Navigator mNavigator;

    ProductSearchResultViewModel(ProductSearchResultAdapter adapter, Navigator navigator) {
        mAdapter = adapter;
        mNavigator = navigator;
        mAdapter.setOrderListener(this);
        mAdapter.setItemClickListener(this);
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

    public ProductSearchResultAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onSearchSuccess(List<Product> products) {
        mAdapter.updateData(products);
    }

    @Override
    public void onAddToCartSuccess() {
        mNavigator.showToast(R.string.add_to_cart_success);
    }

    @Override
    public void onAddToCartError(Throwable e) {
        mNavigator.showToast(e.getMessage());
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
}

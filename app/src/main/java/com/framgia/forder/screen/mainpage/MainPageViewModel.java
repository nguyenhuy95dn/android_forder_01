package com.framgia.forder.screen.mainpage;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import java.util.Observable;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageViewModel extends Observable implements MainPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {

    private MainPageContract.Presenter mPresenter;
    private ProductAdapter mProductAdapter;
    private ShopAdapter mShopAdapter;

    public MainPageViewModel(ProductAdapter productAdapter, ShopAdapter shopAdapter) {
        mProductAdapter = productAdapter;
        mProductAdapter.setOrderListener(this);
        mProductAdapter.setItemClickListener(this);
        mShopAdapter = shopAdapter;
        mShopAdapter.setItemClickListener(this);
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
    public void setPresenter(MainPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        // TODO implement later in here
    }

    @Override
    public void onAddToCart(Product product) {
        // TODO implement later in here
    }

    public ProductAdapter getProductAdapter() {
        return mProductAdapter;
    }

    public ShopAdapter getShopAdapter() {
        return mShopAdapter;
    }
}

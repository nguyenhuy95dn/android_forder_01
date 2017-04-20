package com.framgia.forder.screen.listProduct;

import android.content.Context;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.utils.binding.LayoutManagers;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Exposes the data to be used in the Productpage screen.
 */

public class ListProductViewModel extends Observable implements ListProductContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private static final String TAG = "ListProductFragment";

    private Context mContext;
    private Navigator mNavigator;
    private ListProductContract.Presenter mPresenter;
    private ProductAdapter mProductAdapter;

    public ListProductViewModel(Context context, ProductAdapter productAdapter,
            Navigator navigator) {
        mContext = context;
        mProductAdapter = productAdapter;
        mNavigator = navigator;
        mProductAdapter.setOrderListener(this);
        mProductAdapter.setItemClickListener(this);
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
        mProductAdapter.updateData(products);
    }

    @Override
    public void onAddToCartError(BaseException exception) {
        // Todo show dialog message
    }

    @Override
    public void onAddToCartSuccess() {
        // Todo show dialog message
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
        //TODO Click Item
    }

    public LayoutManagers.LayoutManagerFactory getLayoutManager() {
        return LayoutManagers.grid(mContext.getResources().getInteger(R.integer.item_col));
    }

    public ProductAdapter getProductAdapter() {
        return mProductAdapter;
    }
}

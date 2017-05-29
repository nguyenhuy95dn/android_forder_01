package com.framgia.forder.screen.mainpage;

import android.databinding.ObservableField;
import android.view.View;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.listProduct.ListProductFragment;
import com.framgia.forder.screen.listshop.ListShopFragment;
import com.framgia.forder.screen.mainpage.category.CategoryAdapter;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.screen.mainpage.product.ProductAdapter;
import com.framgia.forder.screen.mainpage.shop.ShopAdapter;
import com.framgia.forder.screen.productdetail.ProductDetailFragment;
import com.framgia.forder.screen.shopDetail.ShopDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;
import java.util.Observable;

/**
 * Exposes the data to be used in the Main screen.
 */

public class MainPageViewModel extends Observable implements MainPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>, OrderListener {
    private static final String TAG = "ListProductFragment";

    private final Navigator mNavigator;
    private MainPageContract.Presenter mPresenter;
    private final ProductAdapter mProductAdapter;
    private final ShopAdapter mShopAdapter;
    private final CategoryAdapter mCategoryAdapter;
    private final ObservableField<Integer> mProgressBarVisibilityProduct = new ObservableField<>();
    private final ObservableField<Integer> mProgressBarVisibilityShop = new ObservableField<>();
    private final ObservableField<Integer> mProgressBarVisibilityCategory = new ObservableField<>();

    public MainPageViewModel(ProductAdapter productAdapter, ShopAdapter shopAdapter,
            Navigator navigator, CategoryAdapter categoryAdapter) {
        mProductAdapter = productAdapter;
        mProductAdapter.setOrderListener(this);
        mProductAdapter.setItemClickListener(this);
        mShopAdapter = shopAdapter;
        mShopAdapter.setItemClickListener(this);
        mNavigator = navigator;
        mCategoryAdapter = categoryAdapter;
        mCategoryAdapter.setItemClickListener(this);
        mProgressBarVisibilityProduct.set(View.GONE);
        mProgressBarVisibilityShop.set(View.GONE);
        mProgressBarVisibilityCategory.set(View.GONE);
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
                    ProductDetailFragment.newInstance(product), true, Navigator.RIGHT_LEFT,
                    "ProductDetailFragment");
        }
        if (item instanceof Shop) {
            Shop shop = (Shop) item;
            mNavigator.goNextChildFragment(R.id.layout_content,
                    ShopDetailFragment.newInstance(shop), true, Navigator.RIGHT_LEFT,
                    "ShopDetailFragment");
        }
        if (item instanceof Category) {
            Category category = (Category) item;
            mNavigator.showToast("Click");
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
    public void onGetListProductError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListProductSuccess(List<Product> products) {
        mProductAdapter.updateData(products);
    }

    @Override
    public void onGetListShopError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListShopSuccess(List<Shop> shops) {
        mShopAdapter.updateData(shops);
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
    public void onGetListCategorySuccess(List<Category> categories) {
        mCategoryAdapter.updateData(categories);
    }

    @Override
    public void onGetListCategoryError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onShowProgressbarProduct() {
        mProgressBarVisibilityProduct.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressbarProduct() {
        mProgressBarVisibilityProduct.set(View.GONE);
    }

    @Override
    public void onShowProgressbarShop() {
        mProgressBarVisibilityShop.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressbarShop() {
        mProgressBarVisibilityShop.set(View.GONE);
    }

    @Override
    public void onShowProgressbarCategory() {
        mProgressBarVisibilityCategory.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressbarCategory() {
        mProgressBarVisibilityCategory.set(View.GONE);
    }

    public void onSeeMoreShopClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListShopFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public void onSeeMoreProductClick() {
        mNavigator.goNextChildFragment(R.id.layout_content, ListProductFragment.newInstance(), true,
                Navigator.RIGHT_LEFT, TAG);
    }

    public ProductAdapter getProductAdapter() {
        return mProductAdapter;
    }

    public ShopAdapter getShopAdapter() {
        return mShopAdapter;
    }

    public CategoryAdapter getCategoryAdapter() {
        return mCategoryAdapter;
    }

    public ObservableField<Integer> getProgressBarVisibilityProduct() {
        return mProgressBarVisibilityProduct;
    }

    public ObservableField<Integer> getProgressBarVisibilityShop() {
        return mProgressBarVisibilityShop;
    }

    public ObservableField<Integer> getProgressBarVisibilityCategory() {
        return mProgressBarVisibilityCategory;
    }
}

package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.createProduct.CreateProductFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the ProductShopInfo screen.
 */

public class ProductShopInfoViewModel extends BaseObservable
        implements ProductShopInfoContract.ViewModel {

    private final Navigator mNavigator;
    private final ProductShopInformationAdapter mAdapter;
    private final ShopManagement mShopManagement;
    private ProductShopInfoContract.Presenter mPresenter;

    ProductShopInfoViewModel(Navigator navigator, ProductShopInformationAdapter adapter,
            ShopManagement shopManagement) {
        mNavigator = navigator;
        mAdapter = adapter;
        mShopManagement = shopManagement;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListAllProductShopInformation(mShopManagement.getShop().getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ProductShopInfoContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getListAllProductShopInformation(mShopManagement.getShop().getId());
    }

    @Override
    public void onGetListAllProductShopInformationError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onGetListAllProductShopInformationSuccess(List<Product> products) {
        mAdapter.updateData(products);
    }

    public ProductShopInformationAdapter getAdapter() {
        return mAdapter;
    }

    public void onClickCreateProduct() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                CreateProductFragment.newInstance(mShopManagement), true, Navigator.RIGHT_LEFT,
                "CreateProductFragment");
    }
}

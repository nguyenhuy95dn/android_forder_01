package com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentProductShopManagementBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductShopManagementAdapter;
import com.example.duong.android_forder_01.ui.productdetail.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by tri on 06/03/2017.
 */
public class ProductShopFragment extends Fragment implements ProductShopFragmentContract.View {
    private ProductShopFragmentContract.Presenter mPresenter;
    private List<Product> mProductList = new ArrayList<>();
    private FragmentProductShopManagementBinding mBinding;
    private ObservableField<ProductShopManagementAdapter> mProductAdapter = new ObservableField<>();
    private Shop mShop;

    public static ProductShopFragment newInstance(Shop shop) {
        ProductShopFragment fragment = new ProductShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_shop_management, container, false);
        setPresenter(new ProductShopFragmentPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mShop = (Shop) getArguments().getSerializable(EXTRA_SHOP);
        mProductList.addAll(mShop.getListProduct());
        mBinding.setProductShopManagement(this);
        mProductAdapter
            .set(new ProductShopManagementAdapter(mProductList, getActivity(), mPresenter));
    }

    @Override
    public void setPresenter(ProductShopFragmentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showDetailShop(Product product) {
        startActivity(ProductDetailActivity.getProductDetailIntent(getContext(), product));
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    @Override
    public void showUiUpdateProduct(Product product) {
        // TODO open activity update product
    }

    @Override
    public void showUiAddProduct(Product product) {
        // TODO open activity add product
    }

    public ObservableField<ProductShopManagementAdapter> getProductAdapter() {
        return mProductAdapter;
    }
}

package com.example.duong.android_forder_01.ui.shopdetail.productofshop;

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
import com.example.duong.android_forder_01.data.source.ProductRepository;
import com.example.duong.android_forder_01.data.source.ShoppingCardRepository;
import com.example.duong.android_forder_01.databinding.FragmentProductOfShopBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductAdapter;
import com.example.duong.android_forder_01.ui.home.product.ProductContract;
import com.example.duong.android_forder_01.ui.home.product.ProductPresenter;
import com.example.duong.android_forder_01.ui.productdetail.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

public class ProductOfShopFragment extends Fragment implements ProductContract.View {
    private ProductContract.Presenter mPresenter;
    private FragmentProductOfShopBinding mBinding;
    private List<Product> mProducts = new ArrayList<>();
    private ObservableField<ProductAdapter> mProductAdapter = new ObservableField<>();
    private Shop mShop;

    public static ProductOfShopFragment newInstance(Shop shop) {
        ProductOfShopFragment fragment = new ProductOfShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_of_shop, container, false);
        setPresenter(new ProductPresenter(this, ProductRepository.getInstance(),
            ShoppingCardRepository.getInstance(getActivity()), getActivity()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void initRecyclerView() {
        mProductAdapter.set(new ProductAdapter(mProducts, getActivity(), mPresenter));
    }

    @Override
    public void showProductDetail(Product product) {
        startActivity(ProductDetailActivity.getProductDetailIntent(getActivity(), product));
    }

    @Override
    public void showAllProduct(List<Product> list) {
        // not required
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    @Override
    public void updateCard(int numberItem) {
        //todo send broadcast update card
    }

    @Override
    public void start() {
        mShop = (Shop) getArguments().getSerializable(EXTRA_SHOP);
        mProducts.addAll(mShop.getListProduct());
        mBinding.setProductOfShopFragment(this);
        initRecyclerView();
    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ProductAdapter> getProductAdapter() {
        return mProductAdapter;
    }
}

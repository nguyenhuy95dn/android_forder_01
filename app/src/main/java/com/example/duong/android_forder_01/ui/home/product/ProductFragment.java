package com.example.duong.android_forder_01.ui.home.product;

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
import com.example.duong.android_forder_01.databinding.FragmentProductBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductAdapter;
import com.example.duong.android_forder_01.ui.productdetail.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment
    implements ProductContract.View {
    private ProductContract.Presenter mPresenter;
    private List<Product> mProducts = new ArrayList<>();
    private ObservableField<ProductAdapter> mProductAdapter = new ObservableField<>();
    private FragmentProductBinding mBinding;

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product, container, false);
        setPresenter(new ProductPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mBinding.setProductFragment(this);
        initRecyclerView();
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
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ProductAdapter> getProductAdapter() {
        return mProductAdapter;
    }
}

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
import com.example.duong.android_forder_01.data.source.ProductRepository;
import com.example.duong.android_forder_01.data.source.ShoppingCardRepository;
import com.example.duong.android_forder_01.databinding.FragmentProductBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductAdapter;
import com.example.duong.android_forder_01.ui.home.HomeActivity;
import com.example.duong.android_forder_01.ui.productdetail.ProductDetailActivity;
import com.example.duong.android_forder_01.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.ID_DOMAIN;

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
        setPresenter(new ProductPresenter(this, ProductRepository.getInstance(),
            ShoppingCardRepository.getInstance(getActivity()), getActivity()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mPresenter.getAllProduct(ID_DOMAIN, SharedPreferencesUtils.loadUser(getActivity()));
        mBinding.setProductFragment(this);
        initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        mProductAdapter.set(new ProductAdapter(getActivity(), mProducts, mPresenter));
    }

    @Override
    public void showProductDetail(Product product) {
        startActivity(ProductDetailActivity.getProductDetailIntent(getActivity(), product));
    }

    @Override
    public void showAllProduct(List<Product> list) {
        if (list == null) return;
        mProducts.clear();
        mProducts.addAll(list);
        mProductAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    @Override
    public void updateCard(int numberItem) {
        HomeActivity.sTextNumberItem.setText(String.valueOf(numberItem));
    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ProductAdapter> getProductAdapter() {
        return mProductAdapter;
    }
}

package com.example.duong.android_forder_01.ui.home.product;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.databinding.FragmentRecyclerviewBinding;
import com.example.duong.android_forder_01.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment
    implements ProductContract.View {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private ProductContract.Presenter mPresenter;
    private List<Product> mProducts = new ArrayList<>();
    private FragmentRecyclerviewBinding mFragmentRecyclerviewBinding;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentRecyclerviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recyclerview, container, false);
        setPresenter(new ProductPresenter(this));
        mPresenter.start();
        return mFragmentRecyclerviewBinding.getRoot();
    }

    @Override
    public void start() {
        initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView = mFragmentRecyclerviewBinding.recyclerView;
        mProductAdapter = new ProductAdapter(mProducts, getActivity(), mPresenter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(
            new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.item_col)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

package com.example.duong.android_forder_01.ui.home.shop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentRecyclerviewBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements ShopContract.View {
    private RecyclerView mRecyclerView;
    private ShopAdapter mShopAdapter;
    private ShopContract.Presenter mPresenter;
    private List<Shop> mShops = new ArrayList<>();
    private FragmentRecyclerviewBinding mFragmentRecyclerviewBinding;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentRecyclerviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_recyclerview, container, false);
        setPresenter(new ShopPresenter(this));
        mPresenter.start();
        return mFragmentRecyclerviewBinding.getRoot();
    }

    @Override
    public void start() {
        initRecyclerView();
    }

    @Override
    public void setPresenter(ShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView = mFragmentRecyclerviewBinding.recyclerView;
        mShopAdapter = new ShopAdapter(mShops, getActivity(), mPresenter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mShopAdapter);
    }

    @Override
    public void openShopDetail(Shop shop) {
        //open shop detail activity and pass shop object
    }
}


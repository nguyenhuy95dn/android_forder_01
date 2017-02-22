package com.example.duong.android_forder_01.ui.home.shop;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentShopBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements ShopContract.View {
    private ObservableField<ShopAdapter> mShopAdapter = new ObservableField<>();
    private ShopContract.Presenter mPresenter;
    private List<Shop> mShops = new ArrayList<>();
    private FragmentShopBinding mBinding;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shop, container, false);
        setPresenter(new ShopPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mBinding.setShopFragment(this);
        initRecyclerView();
    }

    @Override
    public void setPresenter(ShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initRecyclerView() {
        mShopAdapter.set(new ShopAdapter(mShops, getActivity(), mPresenter));
    }

    @Override
    public void openShopDetail(Shop shop) {
        // TODO: open shop detail activity and pass shop object
    }

    public ObservableField<ShopAdapter> getShopAdapter() {
        return mShopAdapter;
    }
}


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
import com.example.duong.android_forder_01.data.source.ShopRepository;
import com.example.duong.android_forder_01.databinding.FragmentShopBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopAdapter;
import com.example.duong.android_forder_01.ui.shopdetail.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.duong.android_forder_01.utils.Const.ID_DOMAIN;

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
        setPresenter(new ShopPresenter(this, ShopRepository.getInstance()));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mPresenter.getAllShop(ID_DOMAIN);
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
        startActivity(ShopDetailActivity.getShopDetailIntent(getActivity(), shop));
    }

    @Override
    public void showAllShop(List<Shop> list) {
        if (list == null) return;
        mShops.addAll(list);
    }

    @Override
    public void showGetDataError() {
        // TODO show get data error
    }

    public ObservableField<ShopAdapter> getShopAdapter() {
        return mShopAdapter;
    }
}


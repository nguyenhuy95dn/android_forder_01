package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop;

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
import com.example.duong.android_forder_01.databinding.FragmentOrderOfShopBinding;
import com.example.duong.android_forder_01.ui.adapter.OrderOfShopViewPagerAdapter;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

public class OrderOfShopFragment extends Fragment implements OrderOfShopContract.View {
    private FragmentOrderOfShopBinding mBinding;
    private OrderOfShopContract.Presenter mPresenter;
    private ObservableField<OrderOfShopViewPagerAdapter> mAdapter = new ObservableField<>();
    private Shop mShop;

    public static OrderOfShopFragment newInstance(Shop shop) {
        OrderOfShopFragment fragment = new OrderOfShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order_of_shop, container, false);
        setPresenter(new OrderOfShopPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mShop = (Shop) getArguments().getSerializable(EXTRA_SHOP);
        mBinding.setOrderOfShop(this);
        mAdapter
            .set(new OrderOfShopViewPagerAdapter(getChildFragmentManager(), getContext(), mShop));
    }

    @Override
    public void setPresenter(OrderOfShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<OrderOfShopViewPagerAdapter> getAdapter() {
        return mAdapter;
    }
}

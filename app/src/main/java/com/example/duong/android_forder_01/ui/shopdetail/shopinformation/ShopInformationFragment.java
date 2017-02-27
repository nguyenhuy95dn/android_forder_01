package com.example.duong.android_forder_01.ui.shopdetail.shopinformation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentShopInformationBinding;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

public class ShopInformationFragment extends Fragment implements ShopInformationContract.View {
    private FragmentShopInformationBinding mBinding;
    private ShopInformationContract.Presenter mPresenter;
    private Shop mShop;

    public static ShopInformationFragment newInstance() {
        return new ShopInformationFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shop_information, container, false);
        setPresenter(new ShopInformationPresenter(this));
        mPresenter.start();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mShop = (Shop) getActivity().getIntent().getSerializableExtra(EXTRA_SHOP);
        mBinding.setShopInformation(this);
    }

    @Override
    public void setPresenter(ShopInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public Shop getShop() {
        return mShop;
    }
}

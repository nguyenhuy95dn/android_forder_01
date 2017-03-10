package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.FragmentCheckOrderBinding;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by Duong on 3/10/2017.
 */
public class CheckOrderFragment extends Fragment {
    private FragmentCheckOrderBinding mBinding;

    public static CheckOrderFragment newInstance(Shop shop) {
        CheckOrderFragment fragment = new CheckOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_check_order, container, false);
        return mBinding.getRoot();
    }
}

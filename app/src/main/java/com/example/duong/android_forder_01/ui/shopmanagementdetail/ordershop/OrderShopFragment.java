package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.duong.android_forder_01.data.model.ShopManagement;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by tri on 06/03/2017.
 */
public class OrderShopFragment extends Fragment {
    public static OrderShopFragment newInstance(ShopManagement shop) {
        OrderShopFragment fragment = new OrderShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }
}

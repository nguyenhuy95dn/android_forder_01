package com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.duong.android_forder_01.data.model.ShopManagement;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by tri on 06/03/2017.
 */
public class ProductShopFragment extends Fragment {
    public static ProductShopFragment newInstance(ShopManagement shop) {
        ProductShopFragment fragment = new ProductShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }
}

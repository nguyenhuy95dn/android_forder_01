package com.example.duong.android_forder_01.ui.shopmanagementdetail.historyshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.duong.android_forder_01.data.model.ShopManagement;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

/**
 * Created by tri on 06/03/2017.
 */
public class HistoryShopFragment extends Fragment {
    public static HistoryShopFragment newInstance(ShopManagement shop) {
        HistoryShopFragment fragment = new HistoryShopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHOP, shop);
        fragment.setArguments(bundle);
        return fragment;
    }
}

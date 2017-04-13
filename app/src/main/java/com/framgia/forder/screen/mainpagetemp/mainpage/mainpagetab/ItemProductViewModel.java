package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemProductViewModel extends BaseObservable {
    private Product mProduct;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> mItemClickListener;

    public ItemProductViewModel(Product product,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> listener) {
        mProduct = product;
        mItemClickListener = listener;
    }
}

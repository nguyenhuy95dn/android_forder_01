package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.BR;
import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.home.shop.ShopContract;
import com.example.duong.android_forder_01.ui.home.shop.ShopItemActionHandler;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.CategoryViewHolder> {
    private List<Shop> mListShop;
    private LayoutInflater mLayoutInflater;
    private ShopContract.Presenter mListener;

    public ShopAdapter(List<Shop> list, Context context, ShopContract.Presenter presenter) {
        mListShop = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_shop, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindData(mListShop.get(position));
    }

    @Override
    public int getItemCount() {
        return mListShop != null ? mListShop.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public CategoryViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Shop shop) {
            if (shop == null) return;
            mDataBinding.setVariable(BR.shop, shop);
            mDataBinding.setVariable(BR.actionHandler, new ShopItemActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}

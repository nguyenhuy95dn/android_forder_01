package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.ItemShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseRecyclerViewAdapter<ShopAdapter.ItemViewHolder> {

    private List<Shop> mShops;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Shop> mItemClickListener;

    protected ShopAdapter(@NonNull Context context, List<Shop> shops) {
        super(context);
        mShops = new ArrayList<>();
        if (shops == null) {
            return;
        }
        mShops.addAll(shops);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShopBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_shop, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mShops.get(position));
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Shop> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemShopBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Shop> mItemClickListener;

        ItemViewHolder(ItemShopBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Shop> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Shop shop) {
            mBinding.executePendingBindings();
        }
    }
}

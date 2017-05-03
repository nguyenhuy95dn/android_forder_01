package com.framgia.forder.screen.shopmanagement.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.ItemShopManagementBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.shop.ItemShopViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ListShopManagementAdapter
        extends BaseRecyclerViewAdapter<ListShopManagementAdapter.ItemViewHolder> {

    private List<Shop> mShops;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    public ListShopManagementAdapter(@NonNull Context context, List<Shop> shops) {
        super(context);
        mShops = new ArrayList<>();
        if (shops == null) {
            return;
        }
        mShops.addAll(shops);
    }

    @Override
    public ListShopManagementAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemShopManagementBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_shop_management, parent, false);
        return new ListShopManagementAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ListShopManagementAdapter.ItemViewHolder holder, int position) {
        holder.bind(mShops.get(position));
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<Shop> shops) {
        if (shops == null) {
            return;
        }
        mShops.clear();
        mShops.addAll(shops);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemShopManagementBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

        ItemViewHolder(ItemShopManagementBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Shop shop) {
            mBinding.setViewModel(new ItemShopViewModel(shop, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}

package com.framgia.forder.screen.listshop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.databinding.ItemListShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.shop.ItemShopViewModel;
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 20-04-2017.
 */

public class ListShopAdapter extends BaseRecyclerViewAdapter<ListShopAdapter.ItemViewHolder> {

    private final List<Shop> mShops;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private AnimationManager mAnimationManager;

    public ListShopAdapter(@NonNull Context context, List<Shop> shops) {
        super(context);
        mShops = new ArrayList<>();
        if (shops == null) {
            return;
        }
        mShops.addAll(shops);
        mAnimationManager = new AnimationManager(context);
    }

    @Override
    public ListShopAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListShopBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_shop, parent, false);
        return new ListShopAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ListShopAdapter.ItemViewHolder holder, int position) {
        holder.bind(mShops.get(position));
        mAnimationManager.animationSlideInLeft(holder.itemView, position);
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

        private final ItemListShopBinding mBinding;
        private final OnRecyclerViewItemClickListener<Object> mItemClickListener;

        ItemViewHolder(ItemListShopBinding binding,
                OnRecyclerViewItemClickListener<Object> listener) {
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

package com.framgia.forder.screen.mainpage.shop;

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
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseRecyclerViewAdapter<ShopAdapter.ItemViewHolder> {

    private final List<Shop> mShops;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private final AnimationManager mAnimationManager;

    public ShopAdapter(@NonNull Context context, List<Shop> shops) {
        super(context);
        mShops = new ArrayList<>();
        mAnimationManager = new AnimationManager(getContext());
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
        mAnimationManager.animationSlideTopIn(holder.itemView, position);
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

        private final ItemShopBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        ItemViewHolder(ItemShopBinding binding,
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

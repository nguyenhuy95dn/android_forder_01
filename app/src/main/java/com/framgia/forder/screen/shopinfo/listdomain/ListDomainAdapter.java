package com.framgia.forder.screen.shopinfo.listdomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.databinding.ItemListDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 6/23/17.
 */

public class ListDomainAdapter extends BaseRecyclerViewAdapter<ListDomainAdapter.ItemViewHolder> {

    private final List<ShopManagement> mShopManagements;
    private AnimationManager mAnimationManager;

    public ListDomainAdapter(@NonNull Context context) {
        super(context);
        mShopManagements = new ArrayList<>();
        mAnimationManager = new AnimationManager(context);
    }

    @Override
    public ListDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_domain, parent, false);
        return new ListDomainAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mShopManagements.get(position));
        mAnimationManager.animationSlideInLeft(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mShopManagements.size();
    }

    public void updateData(List<ShopManagement> shopManagements) {
        if (shopManagements == null) {
            return;
        }
        mShopManagements.clear();
        mShopManagements.addAll(shopManagements);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemListDomainBinding mBinding;

        ItemViewHolder(ItemListDomainBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(ShopManagement shopManagement) {
            mBinding.setViewModel(new ItemListDomainViewModel(shopManagement));
            mBinding.executePendingBindings();
        }
    }
}

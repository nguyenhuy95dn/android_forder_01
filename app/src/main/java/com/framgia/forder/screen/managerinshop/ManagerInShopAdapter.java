package com.framgia.forder.screen.managerinshop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OwnerShop;
import com.framgia.forder.databinding.ItemManagerInShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 6/25/2017.
 */

public class ManagerInShopAdapter
        extends BaseRecyclerViewAdapter<ManagerInShopAdapter.ItemViewHolder> {

    private final List<OwnerShop> mOwnerShops;

    ManagerInShopAdapter(@NonNull Context context) {
        super(context);
        mOwnerShops = new ArrayList<>();
    }

    @Override
    public ManagerInShopAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemManagerInShopBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_manager_in_shop, parent, false);
        return new ManagerInShopAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ManagerInShopAdapter.ItemViewHolder holder, int position) {
        holder.bind(mOwnerShops.get(position));
    }

    @Override
    public int getItemCount() {
        return mOwnerShops.size();
    }

    public void updateData(List<OwnerShop> ownerShops) {
        if (ownerShops == null) {
            return;
        }
        mOwnerShops.clear();
        mOwnerShops.addAll(ownerShops);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemManagerInShopBinding mBinding;

        ItemViewHolder(ItemManagerInShopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(OwnerShop ownerShop) {
            mBinding.setViewModel(new ItemManagerInShopViewModel(ownerShop));
            mBinding.executePendingBindings();
        }
    }
}

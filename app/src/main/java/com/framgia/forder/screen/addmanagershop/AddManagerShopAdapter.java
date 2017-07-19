package com.framgia.forder.screen.addmanagershop;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.databinding.ItemAddManagerInShopBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 7/19/17.
 */

public class AddManagerShopAdapter
        extends BaseRecyclerViewAdapter<AddManagerShopAdapter.ItemViewHolder> {

    private final List<User> mUsers;

    AddManagerShopAdapter(@NonNull Context context) {
        super(context);
        mUsers = new ArrayList<>();
    }

    @Override
    public AddManagerShopAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAddManagerInShopBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_add_manager_in_shop, parent, false);
        return new AddManagerShopAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AddManagerShopAdapter.ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateData(List<User> users) {
        if (users == null) {
            return;
        }
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemAddManagerInShopBinding mBinding;

        ItemViewHolder(ItemAddManagerInShopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(User user) {
            mBinding.setViewModel(new ItemAddManagerInShopViewModel(user));
            mBinding.executePendingBindings();
        }
    }
}

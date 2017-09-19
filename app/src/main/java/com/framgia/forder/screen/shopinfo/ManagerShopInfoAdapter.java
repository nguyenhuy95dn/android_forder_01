package com.framgia.forder.screen.shopinfo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.databinding.ItemManagerBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ManagerShopInfoAdapter
        extends BaseRecyclerViewAdapter<ManagerShopInfoAdapter.ItemViewHolder> {

    private final List<ManagerResponse.ManagerDetail> mUsers;
    private OnRecyclerViewItemClickListener<ManagerResponse.ManagerDetail> mItemClickListener;

    ManagerShopInfoAdapter(@NonNull Context context) {
        super(context);
        mUsers = new ArrayList<>();
    }

    @Override
    public ManagerShopInfoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemManagerBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_manager, parent, false);
        return new ManagerShopInfoAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    public void setItemClickListener(
            OnRecyclerViewItemClickListener<ManagerResponse.ManagerDetail> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(ManagerShopInfoAdapter.ItemViewHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updateData(List<ManagerResponse.ManagerDetail> users) {
        if (users == null) {
            return;
        }
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemManagerBinding mBinding;
        private final OnRecyclerViewItemClickListener<ManagerResponse.ManagerDetail>
                mItemClickListener;

        ItemViewHolder(ItemManagerBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<ManagerResponse
                        .ManagerDetail> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(ManagerResponse.ManagerDetail managerDetail) {
            mBinding.setViewModel(
                    new ItemManagerShopInfoViewModel(managerDetail, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}


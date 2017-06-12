package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.databinding.ItemDoneOrdersBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 6/12/17.
 */

public class ListDoneOrderAdapter
        extends BaseRecyclerViewAdapter<ListDoneOrderAdapter.ItemViewHolder> {
    private final List<OrderHistory> mOrderHistories;

    protected ListDoneOrderAdapter(@NonNull Context context) {
        super(context);
        mOrderHistories = new ArrayList<>();
    }

    @Override
    public ListDoneOrderAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDoneOrdersBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_order_history, parent, false);
        return new ListDoneOrderAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListDoneOrderAdapter.ItemViewHolder holder, int position) {
        holder.bind(mOrderHistories.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderHistories.size();
    }

    public void upDateData(List<OrderHistory> orderHistories) {
        if (orderHistories == null) {
            return;
        }
        mOrderHistories.clear();
        mOrderHistories.addAll(orderHistories);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemDoneOrdersBinding mBinding;

        ItemViewHolder(ItemDoneOrdersBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(OrderHistory orderHistory) {
            mBinding.setViewModel(new ListDoneOrdersViewModel(orderHistory));
            mBinding.executePendingBindings();
        }
    }
}

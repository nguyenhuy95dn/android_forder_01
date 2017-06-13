package com.framgia.forder.screen.orderhistoryshop.listrejectorders;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.databinding.ItemRejectOrdersBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 6/13/17.
 */

public class ListRejectOrdersAdapter
        extends BaseRecyclerViewAdapter<ListRejectOrdersAdapter.ItemViewHolder> {
    private final List<OrderHistory> mOrderHistories;

    ListRejectOrdersAdapter(@NonNull Context context) {
        super(context);
        mOrderHistories = new ArrayList<>();
    }

    @Override
    public ListRejectOrdersAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemRejectOrdersBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_reject_orders, parent, false);
        return new ListRejectOrdersAdapter.ItemViewHolder(getContext(), binding);
    }

    @Override
    public void onBindViewHolder(ListRejectOrdersAdapter.ItemViewHolder holder, int position) {
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

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final ItemRejectOrdersBinding mBinding;

        ItemViewHolder(Context context, ItemRejectOrdersBinding binding) {
            super(binding.getRoot());
            mContext = context;
            mBinding = binding;
        }

        void bind(OrderHistory orderHistory) {
            mBinding.setViewModel(
                    new ListRejectOrdersViewModel(mContext, null, null, orderHistory, null));
            mBinding.executePendingBindings();
        }
    }
}

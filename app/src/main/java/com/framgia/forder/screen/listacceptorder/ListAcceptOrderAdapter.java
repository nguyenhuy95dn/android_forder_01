package com.framgia.forder.screen.listacceptorder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.databinding.ItemListAcceptOrderBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 7/6/2017.
 */

public class ListAcceptOrderAdapter
        extends BaseRecyclerViewAdapter<ListAcceptOrderAdapter.ItemViewHolder> {

    private final List<OrderDetail> mOrderDetails;

    ListAcceptOrderAdapter(@NonNull Context context) {
        super(context);
        mOrderDetails = new ArrayList<>();
    }

    @Override
    public ListAcceptOrderAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemListAcceptOrderBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_accept_order, parent, false);
        return new ListAcceptOrderAdapter.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListAcceptOrderAdapter.ItemViewHolder holder, int position) {
        holder.bind(mOrderDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderDetails.size();
    }

    public void updateData(List<OrderDetail> orderDetails) {
        if (orderDetails == null) {
            return;
        }
        mOrderDetails.clear();
        mOrderDetails.addAll(orderDetails);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemListAcceptOrderBinding mBinding;

        ItemViewHolder(ItemListAcceptOrderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(OrderDetail orderDetail) {
            mBinding.setViewModel(new ItemListAcceptOrderViewModel(orderDetail));
            mBinding.executePendingBindings();
        }
    }
}

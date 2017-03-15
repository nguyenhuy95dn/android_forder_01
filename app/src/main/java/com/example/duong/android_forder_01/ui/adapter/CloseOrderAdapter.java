package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duong.android_forder_01.BR;
import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.OrderDetail;

import java.util.List;

/**
 * Created by Duong on 3/15/2017.
 */
public class CloseOrderAdapter
    extends RecyclerView.Adapter<CloseOrderAdapter.CloseOrderViewHolder> {
    private List<OrderDetail> mOrderDetails;
    private LayoutInflater mLayoutInflater;

    public CloseOrderAdapter(Context context, List<OrderDetail> list) {
        mOrderDetails = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CloseOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_close_order, parent, false);
        return new CloseOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CloseOrderViewHolder holder, int position) {
        holder.bindData(mOrderDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderDetails != null ? mOrderDetails.size() : 0;
    }

    public class CloseOrderViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public CloseOrderViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(OrderDetail orderDetail) {
            if (orderDetail == null) return;
            mDataBinding.setVariable(BR.orderDetail, orderDetail);
            mDataBinding.executePendingBindings();
        }
    }
}

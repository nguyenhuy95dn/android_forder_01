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
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.domain.detaildomain.shopdomain.ShopDomainContract;

import java.util.List;

/**
 * Created by Vinh on 08/03/2017.
 */
public class ShopDomainAdapter
    extends RecyclerView.Adapter<ShopDomainAdapter.ShopDomainViewHolder> {
    private List<Shop> mDomainList;
    private LayoutInflater mLayoutInflater;
    private ShopDomainContract.Presenter mListener;

    public ShopDomainAdapter(Context context, List<Shop> domainList,
                             ShopDomainContract.Presenter presenter) {
        mListener = presenter;
        mLayoutInflater = LayoutInflater.from(context);
        mDomainList = domainList;
    }

    @Override
    public ShopDomainAdapter.ShopDomainViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_shop_domain, parent, false);
        return new ShopDomainAdapter.ShopDomainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShopDomainAdapter.ShopDomainViewHolder holder,
                                 int position) {
        holder.bindData(mDomainList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainList != null ? mDomainList.size() : 0;
    }

    public class ShopDomainViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public ShopDomainViewHolder(View itemView) {
            super(itemView);
            mDataBinding = DataBindingUtil.bind(itemView);
        }

        public void bindData(Shop shop) {
            if (shop == null) return;
            mDataBinding.setVariable(BR.shopDomain, shop);
            mDataBinding.executePendingBindings();
        }
    }
}
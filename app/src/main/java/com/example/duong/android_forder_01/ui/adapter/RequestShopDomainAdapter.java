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
import com.example.duong.android_forder_01.ui.domain.detaildomain.requesttodomain.RequestDomainContract;

import java.util.List;

/**
 * Created by Vinh on 15/03/2017.
 */
public class RequestShopDomainAdapter
    extends RecyclerView.Adapter<RequestShopDomainAdapter.RequestShopDomainViewHolder> {
    private List<Shop> mListShop;
    private LayoutInflater mLayoutInflater;
    private RequestDomainContract.Presenter mListener;

    public RequestShopDomainAdapter(Context context, List<Shop> list, RequestDomainContract
        .Presenter presenter) {
        mListShop = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public RequestShopDomainAdapter.RequestShopDomainViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                   int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_request_shopdomain, parent, false);
        return new RequestShopDomainAdapter.RequestShopDomainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequestShopDomainAdapter.RequestShopDomainViewHolder holder,
                                 int position) {
        holder.bindData(mListShop.get(position));
    }

    @Override
    public int getItemCount() {
        return mListShop != null ? mListShop.size() : 0;
    }

    public class RequestShopDomainViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public RequestShopDomainViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Shop shop) {
            if (shop == null) return;
            mDataBinding.setVariable(BR.requestShopDomain, shop);
            mDataBinding.executePendingBindings();
        }
    }
}
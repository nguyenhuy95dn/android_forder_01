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
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.ui.domain.privatedomain.PrivateDomainContract;
import com.example.duong.android_forder_01.ui.domain.privatedomain.PrivateDomainItemActionHandler;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public class PrivateDomainAdapter
    extends RecyclerView.Adapter<PrivateDomainAdapter.PrivateDomainViewHolder> {
    private List<Domain> mDomainList;
    private LayoutInflater mLayoutInflater;
    private PrivateDomainContract.Presenter mListener;

    public PrivateDomainAdapter(List<Domain> domainList, Context context,
                                PrivateDomainContract.Presenter presenter) {
        mListener = presenter;
        mLayoutInflater = LayoutInflater.from(context);
        mDomainList = domainList;
    }

    @Override
    public PrivateDomainAdapter.PrivateDomainViewHolder onCreateViewHolder(ViewGroup parent,
                                                                           int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_private_domain, parent, false);
        return new PrivateDomainAdapter.PrivateDomainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrivateDomainAdapter.PrivateDomainViewHolder holder,
                                 int position) {
        holder.bindData(mDomainList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainList != null ? mDomainList.size() : 0;
    }

    public class PrivateDomainViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public PrivateDomainViewHolder(View itemView) {
            super(itemView);
            mDataBinding = DataBindingUtil.bind(itemView);
        }

        public void bindData(Domain domain) {
            if (domain == null) return;
            mDataBinding.setVariable(BR.privateDomain, domain);
            mDataBinding.setVariable(BR.privateActionHandler, new PrivateDomainItemActionHandler
                (mListener));
            mDataBinding.executePendingBindings();
        }
    }
}

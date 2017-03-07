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
import com.example.duong.android_forder_01.ui.domain.publicdomain.PublicDomainContract;
import com.example.duong.android_forder_01.ui.domain.publicdomain.PublicDomainItemActionHandler;

import java.util.List;

/**
 * Created by Vinh on 07/03/2017.
 */
public class PublicDomainAdapter
    extends RecyclerView.Adapter<PublicDomainAdapter.PublicDomainViewHolder> {
    private List<Domain> mDomainList;
    private LayoutInflater mLayoutInflater;
    private PublicDomainContract.Presenter mListener;

    public PublicDomainAdapter(List<Domain> domainList, Context context,
                               PublicDomainContract.Presenter presenter) {
        mListener = presenter;
        mLayoutInflater = LayoutInflater.from(context);
        mDomainList = domainList;
    }

    @Override
    public PublicDomainAdapter.PublicDomainViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_domain, parent, false);
        return new PublicDomainAdapter.PublicDomainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PublicDomainAdapter.PublicDomainViewHolder holder,
                                 int position) {
        holder.bindData(mDomainList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainList != null ? mDomainList.size() : 0;
    }

    public class PublicDomainViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public PublicDomainViewHolder(View itemView) {
            super(itemView);
            mDataBinding = DataBindingUtil.bind(itemView);
        }

        public void bindData(Domain domain) {
            if (domain == null) return;
            mDataBinding.setVariable(BR.domain, domain);
            mDataBinding.setVariable(BR.actionHandler, new PublicDomainItemActionHandler
                (mListener));
            mDataBinding.executePendingBindings();
        }
    }
}

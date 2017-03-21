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
import com.example.duong.android_forder_01.ui.home.CategoryItemActionHandler;
import com.example.duong.android_forder_01.ui.home.HomeContract;

import java.util.List;

/**
 * Created by tri on 21/03/2017.
 */
public class DomainAdapter extends RecyclerView.Adapter<DomainAdapter.DomainViewHolder> {
    private List<Domain> mDomainList;
    private LayoutInflater mLayoutInflater;
    private HomeContract.Presenter mListener;

    public DomainAdapter(List<Domain> list, Context context,
                         HomeContract.Presenter presenter) {
        mDomainList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public DomainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_horizontal_domain, parent, false);
        return new DomainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DomainViewHolder holder, int position) {
        holder.bindData(mDomainList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainList != null ? mDomainList.size() : 0;
    }

    public class DomainViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public DomainViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Domain domain) {
            if (domain == null) return;
            mDataBinding.setVariable(BR.domain, domain);
            mDataBinding.setVariable(BR.actionHandler, new CategoryItemActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}

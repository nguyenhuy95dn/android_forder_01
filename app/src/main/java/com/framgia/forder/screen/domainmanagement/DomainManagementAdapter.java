package com.framgia.forder.screen.domainmanagement;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.databinding.ItemDomainManagementBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 6/8/2017.
 */

public class DomainManagementAdapter
        extends BaseRecyclerViewAdapter<DomainManagementAdapter.ItemViewHolder> {

    private DomainManagementListener mDomainManagementListener;
    private final List<DomainManagement> mDomainManagements;

    DomainManagementAdapter(@NonNull Context context) {
        super(context);
        mDomainManagements = new ArrayList<>();
    }

    @Override
    public DomainManagementAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemDomainManagementBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_domain_management, parent, false);
        return new DomainManagementAdapter.ItemViewHolder(binding, mDomainManagementListener);
    }

    @Override
    public void onBindViewHolder(DomainManagementAdapter.ItemViewHolder holder, int position) {
        holder.bind(mDomainManagements.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainManagements.size();
    }

    public void setDomainManagementListener(DomainManagementListener domainManagementListener) {
        mDomainManagementListener = domainManagementListener;
    }

    public void updateData(List<DomainManagement> domainManagements) {
        if (domainManagements == null) {
            return;
        }
        mDomainManagements.clear();
        mDomainManagements.addAll(domainManagements);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final DomainManagementListener mDomainManagementListener;
        private final ItemDomainManagementBinding mBinding;

        ItemViewHolder(ItemDomainManagementBinding binding,
                DomainManagementListener domainManagementListener) {
            super(binding.getRoot());
            mBinding = binding;
            mDomainManagementListener = domainManagementListener;
        }

        void bind(DomainManagement domainManagement) {
            mBinding.setViewModel(
                    new ItemDomainManagementViewModel(domainManagement, mDomainManagementListener));
            mBinding.executePendingBindings();
        }
    }
}

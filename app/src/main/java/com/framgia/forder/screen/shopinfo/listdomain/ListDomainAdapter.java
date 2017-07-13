package com.framgia.forder.screen.shopinfo.listdomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
import com.framgia.forder.databinding.ItemListDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 6/23/17.
 */

public class ListDomainAdapter extends BaseRecyclerViewAdapter<ListDomainAdapter.ItemViewHolder> {

    private final Context mContext;
    private final List<DomainToRequestShopResponse.DomainToRequest> mDomains;
    private final AnimationManager mAnimationManager;
    private ShopDomainManagementListener mDomainManagementListener;

    public ListDomainAdapter(@NonNull Context context) {
        super(context);
        mContext = context;
        mDomains = new ArrayList<>();
        mAnimationManager = new AnimationManager(context);
    }

    @Override
    public ListDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_domain, parent, false);
        return new ListDomainAdapter.ItemViewHolder(mContext, binding, mDomainManagementListener);
    }

    @Override
    public void onBindViewHolder(ListDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mDomains.get(position));
        mAnimationManager.animationSlideInLeft(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDomains.size();
    }

    public void updateData(List<DomainToRequestShopResponse.DomainToRequest> domains) {
        if (domains == null) {
            return;
        }
        mDomains.clear();
        mDomains.addAll(domains);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final ItemListDomainBinding mBinding;
        private final ShopDomainManagementListener mDomainManagementListener;

        ItemViewHolder(Context context, ItemListDomainBinding binding,
                ShopDomainManagementListener shopDomainManagementListener) {
            super(binding.getRoot());
            mContext = context;
            mDomainManagementListener = shopDomainManagementListener;
            mBinding = binding;
        }

        void bind(DomainToRequestShopResponse.DomainToRequest domain) {
            mBinding.setViewModel(
                    new ItemListDomainViewModel(mContext, domain, mDomainManagementListener));
            mBinding.executePendingBindings();
        }
    }

    public interface ShopDomainManagementListener {

        void onApplyToDomain(ApplyShopToDomainRequest applyShopToDomainRequest);

        void onLeaveToDomain(int domainId);
    }

    public void setDomainManagementListener(ShopDomainManagementListener domainManagementListener) {
        mDomainManagementListener = domainManagementListener;
    }
}

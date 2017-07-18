package com.framgia.forder.screen.requestshopindomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
import com.framgia.forder.databinding.ItemRequestShopInDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenhuy95dn on 7/18/2017.
 */

public class RequestShopInDomainAdapter
        extends BaseRecyclerViewAdapter<RequestShopInDomainAdapter.ItemViewHolder> {

    private RequestShopInDomainListener mListener;
    private final List<ShopRequestResponse.ShopContain> mShopContains;

    RequestShopInDomainAdapter(@NonNull Context context) {
        super(context);
        mShopContains = new ArrayList<>();
    }

    @Override
    public RequestShopInDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemRequestShopInDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_request_shop_in_domain, parent, false);
        return new RequestShopInDomainAdapter.ItemViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(RequestShopInDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mShopContains.get(position));
    }

    @Override
    public int getItemCount() {
        return mShopContains.size();
    }

    public void setListener(RequestShopInDomainListener listener) {
        mListener = listener;
    }

    public void updateData(List<ShopRequestResponse.ShopContain> shopContains) {
        if (shopContains == null) {
            return;
        }
        mShopContains.clear();
        mShopContains.addAll(shopContains);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final RequestShopInDomainListener mListener;
        private final ItemRequestShopInDomainBinding mBinding;

        ItemViewHolder(ItemRequestShopInDomainBinding binding,
                RequestShopInDomainListener domainManagementListener) {
            super(binding.getRoot());
            mBinding = binding;
            mListener = domainManagementListener;
        }

        void bind(ShopRequestResponse.ShopContain shopContain) {
            mBinding.setViewModel(new ItemRequestShopInDomain(shopContain, mListener));
            mBinding.executePendingBindings();
        }
    }

    public interface RequestShopInDomainListener {

        void onClickRejectShop(Shop shop);

        void onClickAcceptShop(Shop shop);
    }
}

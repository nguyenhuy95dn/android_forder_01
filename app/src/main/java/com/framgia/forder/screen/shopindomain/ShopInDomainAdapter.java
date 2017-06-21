package com.framgia.forder.screen.shopindomain;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.databinding.ItemShopInDomainBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Age on 6/14/2017.
 */

public class ShopInDomainAdapter
        extends BaseRecyclerViewAdapter<ShopInDomainAdapter.ItemViewHolder> {

    private ShopInDomainListener mShopInDomainListener;
    private List<ShopInDomain> mShops;

    ShopInDomainAdapter(@NonNull Context context) {
        super(context);
        mShops = new ArrayList<>();
    }

    @Override
    public ShopInDomainAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShopInDomainBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_shop_in_domain, parent, false);
        return new ShopInDomainAdapter.ItemViewHolder(binding, mShopInDomainListener);
    }

    @Override
    public void onBindViewHolder(ShopInDomainAdapter.ItemViewHolder holder, int position) {
        holder.bind(mShops.get(position));
    }

    public void setShopInDomainListener(ShopInDomainListener shopInDomainListener) {
        mShopInDomainListener = shopInDomainListener;
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public void updateData(List<ShopInDomain> shops) {
        if (shops == null) {
            return;
        }
        mShops.clear();
        mShops.addAll(shops);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemShopInDomainBinding mBinding;
        private ShopInDomainListener mShopInDomainListener;

        ItemViewHolder(ItemShopInDomainBinding binding, ShopInDomainListener shopInDomainListener) {
            super(binding.getRoot());
            mBinding = binding;
            mShopInDomainListener = shopInDomainListener;
        }

        void bind(ShopInDomain shop) {
            mBinding.setViewModel(new ItemShopInDomainViewModel(shop, mShopInDomainListener));
            mBinding.executePendingBindings();
        }
    }
}

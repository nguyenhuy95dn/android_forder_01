package com.framgia.forder.screen.shopinfo.productshopinfo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.ItemProductShopInfoBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ProductShopInformationAdapter
        extends BaseRecyclerViewAdapter<ProductShopInformationAdapter.ItemViewHolder> {

    private final List<Product> mProducts;
    private UpdateProductListener mUpdateProductListener;

    ProductShopInformationAdapter(@NonNull Context context) {
        super(context);
        mProducts = new ArrayList<>();
    }

    @Override
    public ProductShopInformationAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        ItemProductShopInfoBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_product_shop_info, parent, false);
        return new ProductShopInformationAdapter.ItemViewHolder(binding, mUpdateProductListener);
    }

    @Override
    public void onBindViewHolder(ProductShopInformationAdapter.ItemViewHolder holder,
            int position) {
        holder.bind(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void updateData(List<Product> products) {
        if (products == null) {
            return;
        }
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    void setUpdateProductListener(UpdateProductListener listener) {
        mUpdateProductListener = listener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductShopInfoBinding mBinding;
        private final UpdateProductListener mUpdateProductListener;

        ItemViewHolder(ItemProductShopInfoBinding binding,
                UpdateProductListener updateProductListener) {
            super(binding.getRoot());
            mBinding = binding;
            mUpdateProductListener = updateProductListener;
        }

        void bind(Product products) {
            mBinding.setViewModel(
                    new ItemProductShopInformationViewModel(products, mUpdateProductListener));
            mBinding.executePendingBindings();
        }
    }
}

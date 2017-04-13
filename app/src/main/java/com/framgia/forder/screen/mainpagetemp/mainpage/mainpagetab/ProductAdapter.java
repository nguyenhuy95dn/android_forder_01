package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.ItemProductBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseRecyclerViewAdapter<ProductAdapter.ItemViewHolder> {

    private List<Product> mProducts;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> mItemClickListener;

    protected ProductAdapter(@NonNull Context context, List<Product> products) {
        super(context);
        mProducts = new ArrayList<>();
        if (products == null) {
            return;
        }
        mProducts.addAll(products);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_product, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    void setItemClickListener(OnRecyclerViewItemClickListener<Product> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemProductBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> mItemClickListener;

        ItemViewHolder(ItemProductBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Product> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Product product) {
            mBinding.executePendingBindings();
        }
    }
}

package com.framgia.forder.screen.listProduct.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.databinding.ItemListProductBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.screen.mainpage.product.ItemProductViewModel;
import com.framgia.forder.screen.mainpage.product.OrderListener;
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 4/21/17.
 */

public class ListProductAdapter extends BaseRecyclerViewAdapter<ListProductAdapter.ItemViewHolder> {

    private final List<Product> mProducts;
    private OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private OrderListener mOrderListener;
    private AnimationManager mAnimationManager;

    public ListProductAdapter(@NonNull Context context, List<Product> products) {
        super(context);
        mProducts = new ArrayList<>();
        if (products == null) {
            return;
        }
        mProducts.addAll(products);
        mAnimationManager = new AnimationManager(context);
    }

    @Override
    public ListProductAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListProductBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list_product, parent, false);
        return new ListProductAdapter.ItemViewHolder(binding, mItemClickListener, mOrderListener);
    }

    @Override
    public void onBindViewHolder(final ListProductAdapter.ItemViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
        mAnimationManager.animationSlideInLeft(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setOrderListener(OrderListener orderListener) {
        mOrderListener = orderListener;
    }

    public void updateData(List<Product> products) {
        if (products == null) {
            return;
        }
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemListProductBinding mBinding;
        private final OrderListener mOrderListener;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        ItemViewHolder(ItemListProductBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener,
                OrderListener orderListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
            mOrderListener = orderListener;
        }

        void bind(Product product) {
            mBinding.setViewModel(
                    new ItemProductViewModel(product, mItemClickListener, mOrderListener));
            mBinding.executePendingBindings();
        }
    }
}

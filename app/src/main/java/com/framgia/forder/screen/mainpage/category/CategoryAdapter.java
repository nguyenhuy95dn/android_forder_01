package com.framgia.forder.screen.mainpage.category;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.databinding.ItemCategoryBinding;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.widgets.animation.AnimationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/29/17.
 */

public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryAdapter.ItemViewHolder> {

    private final List<Category> mCategories;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;
    private final AnimationManager mAnimationManager;

    public CategoryAdapter(@NonNull Context context, List<Category> categories) {
        super(context);
        mCategories = new ArrayList<>();
        mAnimationManager = new AnimationManager(getContext());
        if (categories == null) {
            return;
        }
        mCategories.addAll(categories);
    }

    @Override
    public CategoryAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_category, parent, false);
        return new CategoryAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ItemViewHolder holder, int position) {
        holder.bind(mCategories.get(position));
        mAnimationManager.animationSlideInLeft(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateData(List<Category> categories) {
        if (categories == null) {
            return;
        }
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemCategoryBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
                mItemClickListener;

        ItemViewHolder(ItemCategoryBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        void bind(Category category) {
            mBinding.setViewModel(new ItemCategoryViewModel(category, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}

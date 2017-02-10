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
import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.ui.home.CategoryItemActionHandler;
import com.example.duong.android_forder_01.ui.home.HomeContract;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mListCategory;
    private LayoutInflater mLayoutInflater;
    private HomeContract.Presenter mListener;

    public CategoryAdapter(List<Category> list, Context context,
                           HomeContract.Presenter presenter) {
        mListCategory = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = presenter;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            mLayoutInflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindData(mListCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return mListCategory != null ? mListCategory.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mDataBinding;

        public CategoryViewHolder(View view) {
            super(view);
            mDataBinding = DataBindingUtil.bind(view);
        }

        public void bindData(Category category) {
            if (category == null) return;
            mDataBinding.setVariable(BR.category, category);
            mDataBinding.setVariable(BR.actionHandler, new CategoryItemActionHandler(mListener));
            mDataBinding.executePendingBindings();
        }
    }
}

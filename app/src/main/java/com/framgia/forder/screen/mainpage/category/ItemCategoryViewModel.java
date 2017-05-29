package com.framgia.forder.screen.mainpage.category;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Category;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 5/29/17.
 */

public class ItemCategoryViewModel extends BaseObservable {
    private final Category mCategory;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemCategoryViewModel(Category category,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mCategory = category;
        mItemClickListener = listener;
    }

    public String getNameCateGory() {
        if (mCategory != null) {
            return mCategory.getName();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mCategory);
    }
}

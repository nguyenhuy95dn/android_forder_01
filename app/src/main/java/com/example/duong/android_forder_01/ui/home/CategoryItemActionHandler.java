package com.example.duong.android_forder_01.ui.home;

import com.example.duong.android_forder_01.data.model.Category;

public class CategoryItemActionHandler {
    private HomeContract.Presenter mListener;

    public CategoryItemActionHandler(HomeContract.Presenter listener) {
        mListener = listener;
    }

    public void itemClick(Category category) {
        if (mListener == null) return;
        mListener.openProductResultActivity(category);
    }
}

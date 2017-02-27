package com.example.duong.android_forder_01.ui.home;

public class CategoryItemActionHandler {
    private HomeContract.Presenter mListener;

    public CategoryItemActionHandler(HomeContract.Presenter listener) {
        mListener = listener;
    }

    public void itemClick(int categoryId) {
        mListener.openProductResultActivity(categoryId);
    }
}

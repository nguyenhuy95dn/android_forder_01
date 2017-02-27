package com.example.duong.android_forder_01.ui.home;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void initViewPager();
        void initToolbar();
        void initCategoryRecyclerView();
        void showAllCategory(List<Category> list);
        void showDomain(List<Domain> domainList);
        void showGetDataError();
        void showListProduct(int categoryId);
    }

    interface Presenter extends BasePresenter {
        void openProductResultActivity(int categoryId);
        void getAllCategory(int domainId);
        void getDomain(int userId);
    }
}

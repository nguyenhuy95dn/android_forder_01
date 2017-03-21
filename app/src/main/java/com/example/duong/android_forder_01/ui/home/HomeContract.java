package com.example.duong.android_forder_01.ui.home;

import com.example.duong.android_forder_01.data.model.Category;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.data.model.User;
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
        void showListProduct(Category category);
        void showDomainPublic(Domain domain);
        void updateCard(int numberItem);
    }

    interface Presenter extends BasePresenter {
        void openDomainPublic(Domain domain);
        void openProductResultActivity(Category category);
        void getAllCategory(int domainId, User user);
        void getDomain(User user);
        void getCardItem(int domainId);
    }
}

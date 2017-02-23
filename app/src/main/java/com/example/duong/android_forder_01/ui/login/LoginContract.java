package com.example.duong.android_forder_01.ui.login;

import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginComplete(User user);
        void showLoginError(String message);
        void showRequestError();
        void initProgressDialog();
        void showProgress(boolean show);
    }

    interface Presenter extends BasePresenter {
        void login(String userName, String password);
    }
}

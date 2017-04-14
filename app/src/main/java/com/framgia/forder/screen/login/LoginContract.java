package com.framgia.forder.screen.login;

import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface LoginContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void onLoginError(Throwable throwable);

        void onLoginSuccess();

        void onInputUserNameError();

        void onInputPasswordError();

        void onLoginClick();

        void onSignUpClick();

        void onForgotPasswordClick();
    }

    interface Presenter extends BasePresenter {
        void login(String userName, String passWord);

        boolean validateDataInput(String userName, String passWord);

        void saveUser(User user);
    }
}

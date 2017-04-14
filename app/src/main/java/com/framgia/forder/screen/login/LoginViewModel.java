package com.framgia.forder.screen.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.chooseDomain.ChooseDomainActivity;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Login screen.
 */

public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {
    public final ObservableField<Integer> progressBarVisibility = new ObservableField<>();
    private Navigator mNavigator;
    private LoginContract.Presenter mPresenter;
    private String mUsernameError;
    private String mPassswordError;
    private String mUsername;
    private String mPassword;
    private User mUser;

    public LoginViewModel(User user, Navigator navigator) {
        mNavigator = navigator;
        mUser = user;
    }

    @Override
    public void onLoginClick() {
        if (!mPresenter.validateDataInput(mUsername, mPassword)) {
            return;
        }
        mPresenter.login(mUsername, mPassword);
    }

    @Override
    public void onSignUpClick() {
        //TODO SignUp Activity!
    }

    @Override
    public void onForgotPasswordClick() {
        //TODO Forgot Password Activity!
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onLoginError(Throwable throwable) {
        //TODO Login Error !
        mNavigator.showToast(R.string.login_error);
    }

    @Override

    public void onLoginSuccess() {
        //TODO Login Success !
        mNavigator.showToast(R.string.login_success);
        mPresenter.saveUser(mUser);
        mNavigator.startActivity(ChooseDomainActivity.class);
        mNavigator.finishActivity();
    }

    @Override
    public void onInputUserNameError() {
        //TODO UserName Error!
        mNavigator.showToast(R.string.text_must_not_emty);
    }

    @Override
    public void onInputPasswordError() {
        //TODO Password Error !
        mNavigator.showToast(R.string.text_must_not_emty);
    }

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Bindable
    public String getUsernameError() {
        return mUsernameError;
    }

    public void setUsernameError(String usernameError) {
        mUsernameError = usernameError;
    }

    @Bindable
    public String getPassswordError() {
        return mPassswordError;
    }

    public void setPassswordError(String passswordError) {
        mPassswordError = passswordError;
    }
}

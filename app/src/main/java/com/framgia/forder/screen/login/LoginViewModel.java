package com.framgia.forder.screen.login;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.screen.chooseDomain.ChooseDomainActivity;
import com.framgia.forder.screen.forgotpassword.ForgotPasswordActivity;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Exposes the data to be used in the Login screen.
 */

public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {

    private final Context mContext;
    private final Application mApplication;
    private final Navigator mNavigator;
    private LoginContract.Presenter mPresenter;
    private String mUsernameError;
    private String mPasswordError;
    private String mUsername;
    private String mPassword;
    private final DialogManager mDialogManager;

    LoginViewModel(Context context, Application application, Navigator navigator,
            DialogManager dialogManager) {
        mContext = context;
        mApplication = application;
        mNavigator = navigator;
        mDialogManager = dialogManager;
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
        mNavigator.showToastCustomActivity(R.string.function_is_being_processed);
    }

    @Override
    public void onForgotPasswordClick() {
        mNavigator.startActivity(ForgotPasswordActivity.class);
    }

    @Override
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
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
    public void onLoginError(BaseException exeption) {
        mNavigator.showToastCustom(exeption.getMessage());
    }

    @Override
    public void onLoginSuccess() {
        mNavigator.startActivity(ChooseDomainActivity.class);
        FOrderServiceClient.initialize(mApplication);
        mNavigator.finishActivity();
    }

    @Override
    public void onInputUserNameError() {
        mUsernameError = mContext.getString(R.string.email_is_Empty);
        notifyPropertyChanged(BR.usernameError);
    }

    @Override
    public void onInputPasswordError() {
        mPasswordError = mContext.getString(R.string.password_is_Empty);
        notifyPropertyChanged(BR.passswordError);
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

    @Bindable
    public String getPassswordError() {
        return mPasswordError;
    }
}

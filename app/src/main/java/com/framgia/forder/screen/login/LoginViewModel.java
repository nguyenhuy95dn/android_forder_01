package com.framgia.forder.screen.login;

import android.view.View;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Login screen.
 */

public class LoginViewModel implements LoginContract.ViewModel {

    private Navigator mNavigator;
    private LoginContract.Presenter mPresenter;

    public LoginViewModel(Navigator navigator) {
        mNavigator = navigator;
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

    public void onLoginButtonClicked(View view) {
        //TODO change later
        mNavigator.startActivity(MainActivity.class);
        mNavigator.finishActivity();
    }
}

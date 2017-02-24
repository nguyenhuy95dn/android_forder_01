package com.example.duong.android_forder_01.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.data.source.UserRepository;
import com.example.duong.android_forder_01.databinding.ActivityLoginBinding;
import com.example.duong.android_forder_01.ui.home.HomeActivity;

import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.loadUser;
import static com.example.duong.android_forder_01.utils.SharedPreferencesUtils.saveUser;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private Toolbar mToolbarLogin;
    private LoginContract.Presenter mLoginPresenter;
    private ActivityLoginBinding mBinding;
    private ProgressDialog mProgressDialog;
    private String mUsername;
    private String mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setPresenter(new LoginPresenter(this, UserRepository.getInstance(this)));
        mLoginPresenter.start();
    }

    @Override
    public void start() {
        if (loadUser(this) != null) startActivity(new Intent(this, HomeActivity.class));
        mBinding.setActivity(this);
        mToolbarLogin = mBinding.toolbarLogin;
        mToolbarLogin.setTitle("");
        initProgressDialog();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    public void loginClick() {
        mLoginPresenter.login(mUsername, mPassWord);
    }

    @Override
    public void loginComplete(User user) {
        saveUser(this, user);
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void showLoginError(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showRequestError() {
        Snackbar.make(findViewById(android.R.id.content),
            getString(R.string.title_connect_server_error), Snackbar
                .LENGTH_LONG)
            .show();
    }

    @Override
    public void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.DialogStyle);
        mProgressDialog.setTitle(getString(R.string.title_check_login));
        mProgressDialog.setMessage(getString(R.string.title_please_wait));
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) mProgressDialog.show();
        else mProgressDialog.hide();
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
    }
}

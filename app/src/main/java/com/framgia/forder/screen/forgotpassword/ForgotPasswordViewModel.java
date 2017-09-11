package com.framgia.forder.screen.forgotpassword;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Forgotpassword screen.
 */

public class ForgotPasswordViewModel extends BaseObservable
        implements ForgotPasswordContract.ViewModel {

    private static final String TAG = "ForgotPasswordViewModel";
    private final Context mContext;
    private final Navigator mNavigator;
    private ForgotPasswordContract.Presenter mPresenter;
    private String mEmailError;
    private String mEmail;

    ForgotPasswordViewModel(Context context, Navigator navigator) {
        mContext = context;
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
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onInputEmailError(String messageEmail) {
        mEmailError = messageEmail;
        notifyPropertyChanged(BR.emailError);
    }

    @Override
    public void onSendEmailError(BaseException error) {
        Log.e(TAG, "onSendEmailError: ", error);
    }

    @Override
    public void onSendEmailSuccess() {
        mNavigator.showToastCustomActivity(R.string.please_check_your_email);
        mNavigator.finishActivity();
    }

    @Bindable
    public String getEmailError() {
        return mEmailError;
    }

    public void onClickSendEmail() {
        mPresenter.sendEmail(mEmail);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    public void validateEmail(String email) {
        mPresenter.validateDataInput(mContext, email);
    }
}

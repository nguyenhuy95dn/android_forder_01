package com.framgia.forder.screen.forgotpassword;

import android.content.Context;
import android.text.TextUtils;
import com.framgia.forder.R;
import com.framgia.forder.data.source.ForgotPasswordRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * Listens to user actions from the UI ({@link ForgotPasswordActivity}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private static final String TAG = ForgotPasswordPresenter.class.getName();

    private final ForgotPasswordContract.ViewModel mViewModel;
    private final ForgotPasswordRepository mForgotPasswordRepository;
    private final CompositeSubscription mCompositeSubscription;

    ForgotPasswordPresenter(ForgotPasswordContract.ViewModel viewModel,
            ForgotPasswordRepository forgotPasswordRepository) {
        mViewModel = viewModel;
        mForgotPasswordRepository = forgotPasswordRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public boolean validateDataInput(Context context, String email) {
        String messageEmail = validateEmailFormat(context, email);
        if (TextUtils.isEmpty(email)) {
            mViewModel.onInputEmailError(context.getString(R.string.email_is_Empty));
            return false;
        }
        if (!TextUtils.isEmpty(messageEmail)) {
            mViewModel.onInputEmailError(messageEmail);
            return false;
        }
        return true;
    }

    @Override
    public String validateEmailFormat(Context context, String value) {
        boolean isValid = EMAIL_ADDRESS.matcher(value).matches();
        String message = isValid ? "" : context.getString(R.string.invalid_email);
        return message;
    }

    @Override
    public void sendEmail(String email) {
        Subscription subscription = mForgotPasswordRepository.sendEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse>() {
                    @Override
                    public void call(BaseResponse response) {
                        mViewModel.onSendEmailSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onSendEmailError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}

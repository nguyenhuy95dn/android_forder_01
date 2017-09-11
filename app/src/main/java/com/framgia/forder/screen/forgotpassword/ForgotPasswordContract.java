package com.framgia.forder.screen.forgotpassword;

import android.content.Context;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ForgotPasswordContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onInputEmailError(String messageEmail);

        void onSendEmailError(BaseException error);

        void onSendEmailSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        boolean validateDataInput(Context context, String email);

        String validateEmailFormat(Context context, String value);

        void sendEmail(String email);
    }
}

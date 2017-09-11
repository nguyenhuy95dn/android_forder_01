package com.framgia.forder.screen.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.forder.R;
import com.framgia.forder.data.source.ForgotPasswordRepository;
import com.framgia.forder.data.source.remote.ForgotPasswordRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityForgotPasswordBinding;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Forgotpassword Screen.
 */
public class ForgotPasswordActivity extends BaseActivity {

    private ForgotPasswordContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Navigator navigator = new Navigator(this);
        mViewModel = new ForgotPasswordViewModel(getApplicationContext(), navigator);

        ForgotPasswordRepository forgotPasswordRepository = new ForgotPasswordRepository(
                new ForgotPasswordRemoteDataSource(FOrderServiceClient.getInstance()));
        ForgotPasswordContract.Presenter presenter =
                new ForgotPasswordPresenter(mViewModel, forgotPasswordRepository);
        mViewModel.setPresenter(presenter);

        ActivityForgotPasswordBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        binding.setViewModel((ForgotPasswordViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}

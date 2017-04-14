package com.framgia.forder.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.UserRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityLoginBinding;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Login Screen.
 */
public class LoginActivity extends BaseActivity {

    private LoginContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getApplicationContext());
        User user = new User();
        Navigator navigator = new Navigator(this);
        mViewModel = new LoginViewModel(user, navigator);
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(FOrderServiceClient.getInstance()),
                        new UserLocalDataSource(prefsApi));
        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, userRepository);
        mViewModel.setPresenter(presenter);
        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
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

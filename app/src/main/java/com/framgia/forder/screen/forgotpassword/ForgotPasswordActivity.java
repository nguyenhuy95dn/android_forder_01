package com.framgia.forder.screen.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.forder.R;
import com.framgia.forder.data.event.NetWorkStateEvent;
import com.framgia.forder.data.source.ForgotPasswordRepository;
import com.framgia.forder.data.source.remote.ForgotPasswordRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityForgotPasswordBinding;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Forgotpassword Screen.
 */
public class ForgotPasswordActivity extends BaseActivity {

    private ForgotPasswordContract.ViewModel mViewModel;
    private DialogManager mDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Navigator navigator = new Navigator(this);
        mDialogManager = new DialogManager(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NetWorkStateEvent event) {
        if (!event.isConnected()) {
            mDialogManager.dialogWarning(R.string.sorry_not_connect_to_internet);
            mDialogManager.show();
        }
    }
}

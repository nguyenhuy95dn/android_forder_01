package com.framgia.forder.screen.chooseDomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.event.NetWorkStateEvent;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityChooseDomainBinding;
import com.framgia.forder.screen.BaseActivity;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.framgia.forder.screen.splash.SplashActivity.PARAMS;

/**
 * ChooseDomain Screen.
 */
public class ChooseDomainActivity extends BaseActivity {

    private ChooseDomainContract.ViewModel mViewModel;
    private DialogManager mDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String params = getIntent().getExtras().getString(PARAMS);
        mDialogManager = new DialogManager(this);

        List<String> mDomains = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, mDomains);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mViewModel =
                new ChooseDomainViewModel(getApplicationContext(), adapter, new Navigator(this),
                        params);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getApplicationContext());
        UserDataSource.LocalDataSource userLocalDataSource = new UserLocalDataSource(prefsApi);

        DomainRepository repository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, userLocalDataSource));
        ChooseDomainContract.Presenter presenter =
                new ChooseDomainPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityChooseDomainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_choose_domain);
        binding.setViewModel((ChooseDomainViewModel) mViewModel);
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

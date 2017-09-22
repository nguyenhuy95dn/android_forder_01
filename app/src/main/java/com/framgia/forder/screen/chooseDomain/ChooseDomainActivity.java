package com.framgia.forder.screen.chooseDomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityChooseDomainBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.screen.splash.SplashActivity.PARAMS;

/**
 * ChooseDomain Screen.
 */
public class ChooseDomainActivity extends AppCompatActivity {

    private ChooseDomainContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String params = getIntent().getExtras().getString(PARAMS);

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
}

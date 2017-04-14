package com.framgia.forder.screen.chooseDomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.ActivityChoosedomainBinding;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

/**
 * ChooseDomain Screen.
 */
public class ChooseDomainActivity extends AppCompatActivity {

    private ChooseDomainContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> mDomains=new ArrayList<>();
        List<Domain> mDomainList=new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, mDomains);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<Domain> adapterDomain = new ArrayAdapter<Domain>(this, R.layout.spinner_item, mDomainList);

        mViewModel = new ChooseDomainViewModel(adapter, adapterDomain, new Navigator(this));

        SharedPrefsApi prefsApi =  new SharedPrefsImpl(getApplicationContext());

        DomainRepository repository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi));
        ChooseDomainContract.Presenter presenter =
                new ChooseDomainPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityChoosedomainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_choosedomain);
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

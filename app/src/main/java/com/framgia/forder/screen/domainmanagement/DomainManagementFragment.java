package com.framgia.forder.screen.domainmanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentDomainManagementBinding;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainFragment;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Domainmanagement Screen.
 */
public class DomainManagementFragment extends Fragment {

    private DomainManagementContract.ViewModel mViewModel;

    public static DomainManagementFragment newInstance() {
        return new DomainManagementFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DomainManagementAdapter domainManagementAdapter =
                new DomainManagementAdapter(getActivity());
        Navigator navigator = new Navigator(getParentFragment());
        AddDomainFragment fragment = new AddDomainFragment();
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel = new DomainManagementViewModel(domainManagementAdapter, navigator, fragment,
                dialogManager);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        UserDataSource.LocalDataSource userLocalDataSource = new UserLocalDataSource(prefsApi);
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, userLocalDataSource));
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));

        DomainManagementContract.Presenter presenter =
                new DomainManagementPresenter(mViewModel, domainRepository, userRepository);
        mViewModel.setPresenter(presenter);

        FragmentDomainManagementBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_domain_management, container,
                        false);
        binding.setViewModel((DomainManagementViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}

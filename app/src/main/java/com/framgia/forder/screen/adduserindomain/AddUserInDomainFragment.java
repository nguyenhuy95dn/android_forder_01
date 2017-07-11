package com.framgia.forder.screen.adduserindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentAddUserInDomainBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Adduserindomain Screen.
 */
public class AddUserInDomainFragment extends Fragment {

    private static final String EXTRA_DOMAIN = "EXTRA_DOMAIN";

    private AddUserInDomainContract.ViewModel mViewModel;

    public static AddUserInDomainFragment newInstance(DomainManagement domainManagement) {
        AddUserInDomainFragment addUserInDomainFragment = new AddUserInDomainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DOMAIN, domainManagement);
        addUserInDomainFragment.setArguments(bundle);
        return addUserInDomainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        DomainManagement domainManagement = (DomainManagement) getArguments().get(EXTRA_DOMAIN);
        AddUserInDomainAdapter addUserInDomainAdapter =
                new AddUserInDomainAdapter(getActivity().getApplicationContext());
        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel =
                new AddUserInDomainViewModel(addUserInDomainAdapter, domainManagement, navigator,
                        dialogManager);

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));

        AddUserInDomainContract.Presenter presenter =
                new AddUserInDomainPresenter(mViewModel, domainRepository);
        mViewModel.setPresenter(presenter);

        FragmentAddUserInDomainBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_add_user_in_domain, container,
                        false);
        binding.setViewModel((AddUserInDomainViewModel) mViewModel);
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

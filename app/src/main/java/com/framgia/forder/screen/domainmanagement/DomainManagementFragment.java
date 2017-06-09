package com.framgia.forder.screen.domainmanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentDomainManagementBinding;

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

        mViewModel = new DomainManagementViewModel();

        DomainManagementContract.Presenter presenter = new DomainManagementPresenter(mViewModel);
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

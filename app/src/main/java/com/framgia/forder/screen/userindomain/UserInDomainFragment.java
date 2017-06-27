package com.framgia.forder.screen.userindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.databinding.FragmentUserInDomainBinding;

/**
 * Userindomain Screen.
 */
public class UserInDomainFragment extends Fragment {

    private static final String EXTRA_DOMAIN = "EXTRA_DOMAIN";

    private UserInDomainContract.ViewModel mViewModel;

    public static UserInDomainFragment newInstance(DomainManagement domainManagement) {
        UserInDomainFragment shopInDomainFragment = new UserInDomainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DOMAIN, domainManagement);
        shopInDomainFragment.setArguments(bundle);
        return shopInDomainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DomainManagement domainManagement = (DomainManagement) getArguments().get(EXTRA_DOMAIN);
        mViewModel = new UserInDomainViewModel();

        UserInDomainContract.Presenter presenter = new UserInDomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentUserInDomainBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_user_in_domain, container,
                        false);
        binding.setViewModel((UserInDomainViewModel) mViewModel);
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

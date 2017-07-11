package com.framgia.forder.screen.adduserindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentAddUserInDomainBinding;

/**
 * Adduserindomain Screen.
 */
public class AddUserInDomainFragment extends Fragment {

    private AddUserInDomainContract.ViewModel mViewModel;

    public static AddUserInDomainFragment newInstance() {
        return new AddUserInDomainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mViewModel = new AddUserInDomainViewModel();

        AddUserInDomainContract.Presenter presenter = new AddUserInDomainPresenter(mViewModel);
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

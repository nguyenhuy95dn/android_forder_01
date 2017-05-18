package com.framgia.forder.screen.createshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentCreateShopBinding;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Createshop Screen.
 */
public class CreateshopFragment extends Fragment {

    private CreateshopContract.ViewModel mViewModel;

    public static CreateshopFragment newInstance() {
        return new CreateshopFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogManager dialogManager = new DialogManager(getActivity());
        mViewModel = new CreateshopViewModel(getActivity(), dialogManager);
        CreateshopContract.Presenter presenter = new CreateshopPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentCreateShopBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_create_shop, container, false);
        binding.setViewModel((CreateshopViewModel) mViewModel);
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

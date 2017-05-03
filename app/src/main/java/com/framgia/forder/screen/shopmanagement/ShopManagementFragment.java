package com.framgia.forder.screen.shopmanagement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentShopmanagementBinding;

/**
 * ShopManagement Screen.
 */
public class ShopManagementFragment extends Fragment {

    private ShopManagementContract.ViewModel mViewModel;

    public static ShopManagementFragment newInstance() {
        return new ShopManagementFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ShopManagementViewModel();

        ShopManagementContract.Presenter presenter = new ShopManagementPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentShopmanagementBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shopmanagement, container,
                        false);
        binding.setViewModel((ShopManagementViewModel) mViewModel);
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

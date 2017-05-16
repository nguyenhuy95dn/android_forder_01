package com.framgia.forder.screen.shopinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentShopinfoBinding;

/**
 * Shopinfo Screen.
 */
public class ShopinfoFragment extends Fragment {

    private ShopinfoContract.ViewModel mViewModel;

    public static ShopinfoFragment newInstance() {
        return new ShopinfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ShopinfoViewModel();

        ShopinfoContract.Presenter presenter = new ShopinfoPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentShopinfoBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_shopinfo, container, false);
        binding.setViewModel((ShopinfoViewModel) mViewModel);
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

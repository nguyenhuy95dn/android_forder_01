package com.framgia.forder.screen.requestshopindomain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.databinding.FragmentRequestShopInDomainBinding;

/**
 * Requestshopindomain Screen.
 */
public class RequestShopInDomainFragment extends Fragment {

    private RequestShopInDomainContract.ViewModel mViewModel;

    public static RequestShopInDomainFragment newInstance() {
        return new RequestShopInDomainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mViewModel = new RequestShopInDomainViewModel();

        RequestShopInDomainContract.Presenter presenter =
                new RequestShopInDomainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        FragmentRequestShopInDomainBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_request_shop_in_domain,
                        container, false);
        binding.setViewModel((RequestShopInDomainViewModel) mViewModel);
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
